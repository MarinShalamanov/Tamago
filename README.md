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


