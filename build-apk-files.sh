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

TREATMENTS_NAME=(
    "baseline"
    "instrumented-nappa-greedy"
    "instrumented-nappa-tfpr"
    "instrumented-paloma"
    "perfect"
)

for treatment in $TREATMENTS_NAME; do
    for app in $APPS_NAME; do
    echo "${treatment}/${app}/"
    done
done