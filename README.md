# MovieDBAssignment

>> Please go through the screenshots and gif of the app flow at the bottom of the page.

## [Download APK](https://github.com/dev-spm/MovieDBAssignment/blob/master/screenshot/movieDb.apk)

## Assignment Objectives
- Write an app to show list of movies. The selection of any movie will load
the detail page.
- a. First page should show Upcoming movies with pagination.
- b. Items should be displayed in grid layout with poster and title.
- c. Second page should show movie details with: Poster, Title,
Overview.
- d. Using Dependency Injection is mandatory.
- e. Writing Unit Test is mandatory
- f. APIs: https://developers.themoviedb.org/
    - i. UPCOMING_MOVIES
    - ii. MOVIE_DETAILS

## Feature Implemented
- MVVM architecture with Coroutines
- Retrofit for Network call
- Navigation Library
- Glide for Image loading
- Dagger Hilt for dependency Injection
- DIFF Utils to ease loading
- Unit test cases

## Architechure and lib used
- MVVM
- Data Binding
- Coroutines
- Clean Architecture
- Dagger Hilt for Dependency Injection

## Unit Testing And Instrumentation Testing
- Written View Model test cases
- Written Few UI test cases

## How to Run the application
- Download the APK link given above or download the project and open in Android Studio and Sync and click on run on any Emulator or Device
- Click on the **MovieDBAssignment** in device dashboard
- It will fetch upcoming movie list from MovieDb Api
- On successful response, it will show list of movies
- On click on any movie, it will show full details of the movie


## What I will do for future release and Missing Part 
- Create Base module for all the common code(BaseFragment, BaseActivity, BaseInterface...)
- Create Network Module Separately.
- Add Proguard Rule
- Add Gradle Flavour
- Optimize code and gradle speed by adding gradle script
- Kotlin Lint, Android studio Lint rule for each commit
- Add crashlytics
- More test case coverage

## Screenshots of the Weather Application
<table>
    <tr>
        <td>
            <img src="https://github.com/dev-spm/MovieDBAssignment/blob/master/screenshot/screenshot1.png" width="170" height="300" />
        </td>
        <td>
            <img src="https://github.com/dev-spm/MovieDBAssignment/blob/master/screenshot/screenshot2.png" width="170" height="300"/>
        </td>
    </tr>
</table> 
