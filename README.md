# Tamago
Example project for the Android course I'm teaching to K9-K12 students in First Orivate Mathematical Gymnasium.

The purpose of the project is to exercise the following topics:

1. Layouts
2. Activities
1. ImageButtons
1. SharedPreferences
1. Custom styled buttons

And sorry for the rest of the document being in English :) 

# Main Activity
По задание искахме на началния екран искахме да има три бутона - **Start**, който да пуска екрана с яйцето и **Reset**, който да рестартира играта и **Stats**, където да има разни статистики за играта. 

За да направим това слагаме трите бутона в `LinearLayout` по стандартния начин:
```xml
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Start"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Reset"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Stats"/>

    </LinearLayout>
```

Единствения има два проблема - искаме бутоните да са в центъра на екрана. За това към `LinearLayout`-а добавяме атрибута `android:gravity="center"`. 


# Tamago Activity
Тук ще е основната функционалност на играта - яйцето, което ще можем да чупим. 
За удобство ще използвам RelativeLayout за цялото activity. Двете айде ще добавим по следния начин:
```xml
    <ImageView
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:src="@drawable/egg"
        android:layout_centerInParent="true"/>

    <ImageView
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:src="@drawable/broken"
        android:layout_centerInParent="true"
        android:visibility="invisible"/>
```

Тук добавих атрибута `android:layout_centerInParent="true"`, за да бъдат яйцата позиционирани в центъра на екрана. А пък `android:visibility="invisible"` сложих, за да не бъде счупеното яйце видимо в начало. Идеята ми е в момента, в който трябва да покажем счупеното да направим първито ImageView `invisible`, а пък второто - `visible`.



# Брояч

Сега да направим броячът на кликовете.

## Дизайн
Под яйцето добавям брояч на кликовете:
```xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="43"
        android:id="@+id/score"
        android:layout_centerHorizontal="true"
        android:layout_alignParentBottom="true"
        android:textSize="42sp"/>
```
Няколко важни момента: 

1. За големината на текста използвам `sp`, защото размерът на текста се определя спрямо големината на екрана и потребителските настройки за големина на текста. 
1. Следният код позиционира броячът най-долу в средата на екрана:
```xml
android:layout_centerHorizontal="true"
android:layout_alignParentBottom="true"
```

## Логика
В `TamagoActivity.java` слагам глобалната променлива за класа `private int numClicks = 0;`, където ще си паза тещуия брой кликове.
В xml-а, на цялото яйце слагам атрибутът `android:onClick="eggClicked"`. Така всеки път когато е кликнато яйцето ще се вика функцията eggClicked. Да добавим и съответната функция в java класа:

```java
public void eggClicked (View view) {
        numClicks++;
        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Integer.toString(numClicks));
}
```
Във функцията увеличавам броя на кликовете, след което обновявам стойността в TextView-то.

Сега като кликаме на яйцето броячът отброява правилно, но ако затворим приложението и го отворим наново, този брой не се е запазил. За да го направим да се запазва, ще използваме `SharedPreferences`! В `eggClicked` след като увелича броя на кликанията, записвам новата стойност в SharedPreference:

```java
public void eggClicked (View view) {
        numClicks++;
        
        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", numClicks);
        editor.apply();
        
        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Integer.toString(numClicks));
}
```

Сега като затворим пролжението и после го отворим на ново броячът ще показва някаква неадекватна стойност, но след като кликнем веднъж всичко ще е наред. За да показва правилната стойност при отваряне, трябва при пускането на приложението да прочетем от SharedPreferences броя на кликовете и да ги изпишем в TextView-то. Това правя във функцията onResume, която се вика непосредствено преди отварянето на приложението:
```java
@Override
protected void onResume() {
        super.onResume();
        
        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        numClicks = preferences.getInt("clicks", 0);
        
        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Integer.toString(numClicks));
}
```

## Чупене на яйцето

Сега остава да чупим яйцето, когато броят на кликанията стане, напр. 10. Това разбира се е лесно:
Към `eggClicked`, след като увелич броя на кликанията добавяме следния код, който скрива цялото яйце и показва счупеното, ако броя на кликанията е станал 10 или повече.
```java
if (numClicks >= 10) {
    ImageView egg = (ImageView)findViewById(R.id.egg);
    ImageView broken = (ImageView)findViewById(R.id.broken);

    egg.setVisibility(View.INVISIBLE);
    broken.setVisibility(View.VISIBLE);
}
```
Сега имаме следния малък проблем - ако счупем яйцето и рестаритараме приложението, яйцето ще се покаже цяло. Но ако кликнем веднъж върху него ще се появи счупеното яйде. Това е така, защото при създаване на acitivity-то винаги показваме цялото яйце. Ако добавим горния код в `onResume`, то преди да се покаже активито ще смяняме картиката на яйцето (ако е нужно). Така оправяме този проблем.

# Reset бутона
Целта на Reset бутона е да стартира играта от началото. За да постигнем това е достатъчно да зануляваме броят на кликанията, които записахме в SharedPreference, при натискането на бутона. Така щом се отвори екрана с яйцето ще се прочете, че броят на кликанията е 0, ще се нарисува цялото яйде и играта ще е започнала от начало.
Това правим по следния начин. Към бутонът Reset в xml-а добавяме `android:onClick="onResetClicked"`. В `MainActivity.java`:
```java
public void onResetClicked(View view) {
    SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt("clicks", 0);
    editor.apply();
}
```

# Статистики
Ще направим екран `StatsActivity` със следните статистики:

1. Брой отваряния на приложението
1. Брой счупвания на яйцето

## Дизайн
В `activity_stats.xml`:
```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/labelAppOpened"
    android:text="Брой отваряния на приложението: "/>

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/labelEggsBroken"
    android:layout_below="@id/labelAppOpened"
    android:text="Брой счупени яйца: "/>

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/statAppOpened"
    android:layout_toRightOf="@id/labelAppOpened"
    android:layout_alignTop="@id/labelAppOpened"
    android:text="0"/>

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/statEggsBroken"
    android:layout_toRightOf="@id/labelEggsBroken"
    android:layout_alignTop="@id/labelEggsBroken"
    android:text="0"/>
```
По този начин направих 4 текстови полета. Първите две за текстовете "Брой отваряния на приложението:" и "Брой счупени яйца: " и другите две за съответния брой. 
Подредил съм ги в `RelativeLayout`, чрез атрибутите `android:layout_below`, `android:layout_toRightOf`, `android:layout_alignTop`.

## Логика
И за двете статистики ще използваме `SharedPreferences`, за да губим данните след затваряне на приложението.

### Брой отваряния на приложението
В `MainActivity.java` в `onCreate` чета записаната стойност за брой отваряния на приложението, увеличавам я с едно и пак я записвам в SharedPreferences.
```java
SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
int statAppsOpened = preferences.getInt("statAppsOpened", 0);
statAppsOpened++;

SharedPreferences.Editor editor = preferences.edit();
editor.putInt("statAppsOpened", statAppsOpened);
editor.apply();
 ```
 
В `StatsActivity.java` в `onCreate`, чета записаната стойност за брой отваряния на приложението и я изписвам на екрана.
```java
SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
int statAppsOpened = preferences.getInt("statAppsOpened", 0);

TextView statAppsOpenedTV = (TextView) findViewById(R.id.statAppOpened);
statAppsOpenedTV.setText(Integer.toString(statAppsOpened));
```

### Брой счупени яйца
Аналогично на горната статистика.
Искаме, когато се счупи яйцето, да увеличим брояча с едно. В `eggClicked` добавяме:
```java
if(numClicks == 10) {
    int statEggsBroken = preferences.getInt("statEggsBroken", 0);
    statEggsBroken++;
    editor.putInt("statEggsBroken", statEggsBroken);
    editor.apply();
}
```
И в `StatsActivity.java` в `onCreate`:
```java
int statEggsBroken = preferences.getInt("statEggsBroken", 0);
TextView statEggsBrokenTV = (TextView) findViewById(R.id.statEggsBroken);
statEggsBrokenTV.setText(Integer.toString(statEggsBroken));
```

# Вече имаме работещо Tamago! :)
Сега можем да го направим още по-готино:
## Нова иконка на приложението
В `AndroidManifest` иконката се задава от `android:icon="@mipmap/ic_launcher"`. За да сменим иконката добавяме новата в папка mipmap. Ако тя се казва logo.png, то променяме атрибутата на: `android:icon="@mipmap/logo"` и вече имаме наша иконка.

## По-хубав дизайн:
1. [Инструмент за генериране на дизайн на бутони](http://angrytools.com/android/button/)
1. Да сменим фона
1. Да направимо бутоните на начния екран по-големи
1. Да разкрасим екрана със статистиките

## Да го качим в Play Store
