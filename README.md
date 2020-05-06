# Subjects

This repository contains the 7 android apps used as subjects in the evaluation of the thesis 
`Prefetching Network Requests in Android Apps: Strategies and Empirical Evaluation` 
submitted for the 2019-2020 edition of 
the [Master Project Computer Science](https://studiegids.vu.nl/en/Master/2019-2020/computer-science/XM_0011) course 
at the [Vrije Universiteit Amsterdam](https://www.vu.nl/en).

## To Do

- Add non-instrumneted version
- Add NAPPA greedy version
- Add NAPPA strategy 2 version
- Add NAPPA strategy 3 version
- Add PALOMA version

## Directories

Each directory contains a different version of the apps.
Overall, the apps in the directory `original-apps` are cloned directly from the original repositories. 
The SDK version from these apps is updated to version 28 and stored in the directory `non-instrumented `.
This is the base version used for instrumenting the apps to enable prefetch mechanisms. 

| Name                          | Description                                                          |
|-------------------------------|----------------------------------------------------------------------|
| original-apps                 | An unmodified version cloned directly from the original repository.  |
| non-instrumented              | A modified version with fixed dependencies compatibility.            |
| instrumented-nappa-greedy     | A prefetch enabled version using NAPPA approach with Greedy strategy |
| instrumented-nappa-strategy-2 | A prefetch enabled version using NAPPA approach with TBD strategy    |
| instrumented-nappa-strategy-3 | A prefetch enabled version using NAPPA approach with TBD strategy    |
| instrumented-paloma           | A prefetch enabled version using PALOMA approach                     |

## Android Apps

To browse the files in the original repository in the cloned commit hash, use the following URL: 
`<repository-url>/tree/<commit-hash>`.

### Hillffair

This is the official Android Application of the Cultural Fest of NIT Hamirpur (Hillffair). 
Describes amenities and events taking place in a festival.

- Original repository: https://github.com/appteam-nith/Hillffair
- Commit hash: 3bc9f8e9e4af3a16cbcbaa6be07cf604c84cf408
- Commit date: June 19, 2018

### UOB Timetable Android

This application displays the 5-day timetable for any University of Bedfordshire course.

- Original repository: https://github.com/adriankeenan/uob-timetable-android
- Commit hash: defd0e9eddde72f130c731e06a968288ad29b06d
- Commit date: September 10, 2019

### NewsBlur

NewsBlur is a personal news reader that brings people together to talk about the world. A new sound of an old instrument. 

- Original repository: https://github.com/samuelclay/NewsBlur
- Commit hash: ba279fc520da4fac7b42d5d6db1de7f6f7fdc5c6
- Commit date: May 29, 2019

### RedReader

An unofficial open source Reddit client for Android. 

- Original repository: https://github.com/QuantumBadger/RedReader
- Commit hash: 708865e5b144091b7ead8b183ca291ad12d3c066
- Commit date: June 2, 2019

### Materialistic for Hacker News

Material design Hacker News client for Android, uses official HackerNews/API, Dagger for dependency injection and Robolectric for unit testing.

- Original repository: https://github.com/hidroh/materialistic
- Commit hash: 55d7dba81a6456fff597b2dcc52f70a89fa069db
- Commit date: Mar 30, 2019

### AntennaPod

This is the official repository of AntennaPod, the easy-to-use, flexible and open-source podcast manager for Android.

- Original repository: https://github.com/AntennaPod/AntennaPod
- Commit hash: 605e02fa70c9c30ecc70548a8813392ba18dda80
- Commit date: June 1, 2019

### Travel Mate

Travel Mate is an android app for travellers. 
It provides features like choosing the correct destination, making bookings, and organizing the trip.

- Original repository: https://github.com/project-travel-mate/Travel-Mate
- Commit hash: 9798102fe15601888df4a72205a4d7304daf52e5
- Commit date: July 7, 2019
