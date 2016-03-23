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

