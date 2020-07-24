#!/bin/zsh

# This script will copy the built apks to the directory /build/apks/treatment

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

APKS_SOURCE=(
    # Baseline
    "${PROJECT_DIR}/baseline/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/baseline/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/baseline_NewsBlur-debug.apk"
    "${PROJECT_DIR}/baseline/RedReader/build/outputs/apk/debug/BaselineRedReader-debug.apk"
    "${PROJECT_DIR}/baseline/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/uob-timetable-android/app/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Nappa Greedy
    "${PROJECT_DIR}/instrumented-nappa-greedy/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/RedReader/build/outputs/apk/debug/RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/uob-timetable-android/app/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Nappa TFPR
    "${PROJECT_DIR}/instrumented-nappa-tfpr/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/RedReader/build/outputs/apk/debug/RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/uob-timetable-android/app/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # PALOMA
    "${PROJECT_DIR}/instrumented-paloma/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/RedReader/build/outputs/apk/debug/RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/uob-timetable-android/app/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Perfect
    "${PROJECT_DIR}/perfect/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/perfect/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/NewsBlur-debug.apk"
    "${PROJECT_DIR}/perfect/RedReader/build/outputs/apk/debug/RedReader-debug.apk"
    "${PROJECT_DIR}/perfect/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/uob-timetable-android/app/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
)

APKS_DST=(
    # Baseline
    "${PROJECT_DIR}/build/apks/baseline/baseline-AntennaPod.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-Hillffair.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-materialistic.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-NewsBlur.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-RedReader.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-Travel-Mate.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline-uob-timetable-android.apk"
    # Nappa Greedy
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-AntennaPod.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-Hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-NewsBlur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-RedReader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-Travel-Mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/instrumented-nappa-greedy-uob-timetable-android.apk"
    # Nappa TFPR
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-AntennaPod.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-Hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-NewsBlur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-RedReader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-Travel-Mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/instrumented-nappa-tfpr-uob-timetable-android.apk"
    # PALOMA
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-AntennaPod.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-Hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-NewsBlur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-RedReader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-Travel-Mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/instrumented-paloma-uob-timetable-android.apk"
    # Perfect
    "${PROJECT_DIR}/build/apks/perfect/perfect-AntennaPod.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-Hillffair.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-materialistic.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-NewsBlur.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-RedReader.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-Travel-Mate.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect-uob-timetable-android.apk"
)

NUMBER_OF_APPS=7

# Removes all stale APK
apk_dir="${PROJECT_DIR}/build/apks"
cd $apk_dir
find . -name "*.apk" -delete

# Copy APKS to destination
for index in {1..$#APKS_SOURCE}; do
    if [ -f "${APKS_SOURCE[index]}" ]; then
        cp "${APKS_SOURCE[index]}" "${APKS_DST[index]}"
    else
        echo "not found ${APKS_SOURCE[index]}"
    fi
    if ! (($index % $NUMBER_OF_APPS)); then
        echo ""
    fi
done

# Write to file the absoule path to all apps
cd $apk_dir
find $PWD -name "*.apk" | sed -e 's/^/"/g' -e 's/$/",/g' >"list-apk-paths.txt"

echo "APKS are located at"
cat "list-apk-paths.txt"
