# Animation-Android
Implementation of a single fragment with animation navigation

Documentation: https://developer.android.com/guide/fragments/animate



![animation](https://github.com/user-attachments/assets/6bfe2eb5-c359-4dac-8030-88eeca845552)



This Android app demonstrates fragment-based navigation using custom animations between two sections of a single fragment (FragmentA). It includes enter and exit animations to create smooth transitions as the user navigates between the two sections within the fragment.

Files and Structure
1. activity_main.xml - Contains the layout for MainActivity with a FragmentContainerView for hosting FragmentA.
2. MainActivity.java - Hosts FragmentA on app launch.
3. fragment_a.xml - Layout for FragmentA, containing two sections (view_section_1 and view_section_2) that the user navigates between using buttons.
   !Each view_section has a TextView, a Button and an ImageView in containg a jpg image.
4. FragmentA.java - Fragment class where the animation and visibility control logic between sections is implemented.
5. Animation XMLs (in res/anim directory) - Stored in the res/anim directory, these define the enter and exit animations for each section.
6. Here's an organized documentation for your Android app, covering both the implementation and usage of animations between sections within a single fragment:


---

### Layout Files

#### 1. `activity_main.xml`

Defines the main layout for `MainActivity`, with a `FragmentContainerView` to host `FragmentA`:

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragmentContainerView"
        android:name="com.example.myapplication.FragmentA"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        tools:layout="@layout/fragment_a"/>
</LinearLayout>
```

#### 2. `fragment_a.xml`

Defines two sections (`view_section_1` and `view_section_2`) within `FragmentA`. Each section contains a `TextView`, a `Button` for navigation, and an `ImageView` for visuals:

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".FragmentA">

    <LinearLayout
        android:id="@+id/view_section_1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center"
        android:background="@color/teal_200">     ///the colors are from res/values/colors.xml   !!!!!!!!!!!!!!!!!!!!!!!!
        
        <!-- Content for Section 1 -->
    </LinearLayout>

    <LinearLayout
        android:id="@+id/view_section_2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center"
        android:visibility="gone"
        android:background="@color/purple_200">   ///the colors are from res/values/colors.xml   !!!!!!!!!!!!!!!!!!!!!!!!

        <!-- Content for Section 2 -->
    </LinearLayout>
</FrameLayout>
```

---

### Java Classes

#### 1. `MainActivity.java`

Sets up `FragmentA` as the initial fragment in the `FragmentContainerView`:

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = new FragmentA();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
}
```

#### 2. `FragmentA.java`

Implements button click listeners and animations to navigate between sections:

```java
public class FragmentA extends Fragment {

    private View viewSection1;
    private View viewSection2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewSection1 = view.findViewById(R.id.view_section_1);
        viewSection2 = view.findViewById(R.id.view_section_2);

        
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSection2();
            }
        });

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSection1();
            }
        });
    }

    private void navigateToSection2() {
        Animation fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out);
        Animation slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in);

        viewSection1.startAnimation(fadeOut);
        viewSection1.setVisibility(View.GONE);

        viewSection2.setVisibility(View.VISIBLE);
        viewSection2.startAnimation(slideIn);
    }

    private void navigateToSection1() {
        Animation slideOut = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out);
        Animation fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);

        viewSection2.startAnimation(slideOut);
        viewSection2.setVisibility(View.GONE);

        viewSection1.setVisibility(View.VISIBLE);
        viewSection1.startAnimation(fadeIn);
    }
}
```

---

### Animation XML Files

#### 1. `fade_out.xml`

Defines a fade-out animation used when navigating away from a section:

```xml
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="@android:integer/config_shortAnimTime"
    android:fromAlpha="1"
    android:toAlpha="0" />
```

#### 2. `slide_in.xml`

Defines a slide-in animation used when a section enters from the right:

```xml
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="@android:integer/config_shortAnimTime"
    android:fromXDelta="100%"
    android:toXDelta="0%" />
```

#### 3. `slide_out.xml`

Defines a slide-out animation used when a section exits to the right:

```xml
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="@android:integer/config_shortAnimTime"
    android:fromXDelta="0%"
    android:toXDelta="100%" />
```

#### 4. `fade_in.xml`

Defines a fade-in animation used when a section becomes visible:

```xml
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="@android:integer/config_shortAnimTime"
    android:fromAlpha="0"
    android:toAlpha="1" />
```

---

### Usage and Navigation

1. **Navigating to Section 2**:
   - `button_next` in `view_section_1` triggers `navigateToSection2()`.
   - `view_section_1` fades out and hides, while `view_section_2` slides in and becomes visible.

2. **Returning to Section 1**:
   - `button_back` in `view_section_2` triggers `navigateToSection1()`.
   - `view_section_2` slides out and hides, while `view_section_1` fades in and becomes visible.

--- 




### Still, in res/anim, what does each attribute mean?

### **Alpha Animation (`alpha.xml`)**

   - **Purpose**: Controls the fade-in or fade-out of the view by adjusting its opacity.

   **Key Attributes**:
   - **`android:fromAlpha`**: Specifies the starting opacity level. For example, `1.0` means fully opaque, and `0.0` means fully transparent.
   - **`android:toAlpha`**: Specifies the ending opacity level. For example, setting this to `0.0` will fade the view out, while setting it to `1.0` will fade it in.
   - **`android:duration`**: The duration (in milliseconds) for the animation to complete, controlling the speed of the fade effect.
   
   **Example**:
   ```xml
   <alpha xmlns:android="http://schemas.android.com/apk/res/android"
       android:fromAlpha="1.0" ///means this is for the first animation (at the strat of the app), since it's fully opaque -> we can see it
       android:toAlpha="0.0"
       android:duration="500" />
   ```
   - In this example, the view will fade out from fully visible to completely transparent over 500 milliseconds.

---

### **Translate Animation (`translate.xml`)**

   - **Purpose**: Moves the view from one position to another, creating a sliding effect.

   **Key Attributes**:
   - **`android:fromXDelta`**: The starting horizontal position offset, relative to the view’s current position. For example, `0%` means it starts at its original position, while `100%` will start from the right of its current position.
   - **`android:toXDelta`**: The ending horizontal position offset. For example, setting this to `0%` will bring the view back to its original position, while `-100%` moves it to the left.
   - **`android:fromYDelta`**: The starting vertical position offset. For example, `0%` keeps it in place, while `100%` moves it downwards.
   - **`android:toYDelta`**: The ending vertical position offset. For instance, setting this to `0%` will return it to the initial vertical position, while a negative value like `-100%` moves it upward.
   - **`android:duration`**: Specifies how long (in milliseconds) the animation should take to complete, controlling the speed of the sliding effect.

   **Example**:
   ```xml
   <translate xmlns:android="http://schemas.android.com/apk/res/android"
       android:fromXDelta="0%"   ///for 1st view section as well, since this one firstly by default exists to the right
       android:toXDelta="100%"  ///clarly, the first comes back to the orginal position on teh horizontal line...think!
       android:duration="500" />
   ```
   - In this example, the view will slide horizontally to the right by 100% of its width over 500 milliseconds.

---

These attributes define the basics of the fade (alpha) and slide (translate) animations for creating smooth transitions between your sections.


### Why Use `android:duration="@android:integer/config_shortAnimTime"`?

Using `@android:integer/config_shortAnimTime` provides a flexible and consistent animation duration that aligns with Android's design standards. Here are the main reasons to use it:

- **System-Defined Standard**:
  - `@android:integer/config_shortAnimTime` is a predefined constant in Android's framework, set to represent a "short" animation duration.
  - It is carefully designed to keep animations consistent with Android's native UI feel and behavior, enhancing app uniformity across the OS.

- **Consistency with System Animations**:
  - By using this value, your app’s animations (e.g., button clicks, view transitions) will match the system's default timing, ensuring your app integrates smoothly with the overall OS experience.

- **Optimal for Short Animations**:
  - This duration is typically used for quick animations that last only a fraction of a second.
  - The exact value may differ based on Android versions and device settings, helping maintain optimal user experience regardless of device specifications.

- **Accessibility-Driven Adaptability**:
  - Some Android devices allow users to adjust animation speeds for accessibility (e.g., choosing reduced motion).
  - Using `config_shortAnimTime` ensures your app's animations automatically adapt to these settings, improving accessibility and user customization.

By referencing `config_shortAnimTime`, you avoid hardcoding a duration value, making your app’s animations both adaptable and user-friendly across various devices and system settings.

### Summary
This app demonstrates the usage of custom animations to create visually smooth transitions between sections within a fragment. The animations enhance user experience, making section navigation visually appealing and intuitive. 

