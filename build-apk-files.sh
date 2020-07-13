#!/bin/zsh

# The identifier name of the apps directories
APPS_NAME=(
    "AntennaPod"
    "Hillffair"
    "materialistic"
    "NewsBlur"
    "RedReader"
    "Travel-Mate"
    "uob-timetable-android"
)

# The relative path to the Android project inside the app project directory
APPS_ANDROID_DIR=(
    "/"                          # AntennaPod
    "/"                          # Hillffair
    "/"                          # materialistic
    "/clients/android/NewsBlur/" # NewsBlur
    "/Android/"                  # Travel-Mate
    "/app/"                      # uob-timetable-android
)

# The identifier name of the treatments directory
TREATMENTS_NAME=(
    "baseline"
    "instrumented-nappa-greedy"
    "instrumented-nappa-tfpr"
    "instrumented-paloma"
    "perfect"
)

for treatment in $TREATMENTS_NAME; do
    for app in $APPS_NAME; do
        echo "Building APK for the app ${app} with treatment ${treatment}."

        echo "Finished building APK."
    done
done
