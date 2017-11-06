# Project 2 - NY Times

NYTimes is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

## User Stories

* [x] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [x] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.
* [x] User can tap on any image in results to see the full text of article **full-screen**

The following **optional** features are implemented:

* [x] User can **share an article link** to their friends or email it to themselves
* [x] Improved the user interface and experiment with image assets and/or styling and coloring
* [x] Replaces the default ActionBar with a [Toolbar](http://guides.codepath.com/android/Using-the-App-ToolBar).
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [x] Replace `GridView` with the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) and the `StaggeredGridLayoutManager` to improve the grid of image results displayed.
* [x] Use Parcelable instead of Serializable leveraging the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [x] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Before an article search is triggered by the user, displays the current top stories of the day by default.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/3z52BkZ.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='http://imgur.com/5iXqJKL.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2016 Mo Bakhiet

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
