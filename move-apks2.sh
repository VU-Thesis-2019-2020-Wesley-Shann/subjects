#!/bin/zsh

# This script will copy the built apks to the directory /build/apks/treatment

# Obtain the absolute path to the project root directory for moving between directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

APKS_SOURCE=(
    # Baseline
    "${PROJECT_DIR}/baseline/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/baseline/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/Baseline NewsBlur-debug.apk"
    "${PROJECT_DIR}/baseline/RedReader/build/outputs/apk/debug/Baseline RedReader-debug.apk"
    "${PROJECT_DIR}/baseline/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/baseline/uob-timetable-android/uob/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Nappa Greedy
    "${PROJECT_DIR}/instrumented-nappa-greedy/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/Nappa Greedy NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/RedReader/build/outputs/apk/debug/Nappa Greedy RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-greedy/uob-timetable-android/uob/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Nappa TFPR
    "${PROJECT_DIR}/instrumented-nappa-tfpr/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/Nappa TFPR NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/RedReader/build/outputs/apk/debug/Nappa TFPR RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-nappa-tfpr/uob-timetable-android/uob/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # PALOMA
    "${PROJECT_DIR}/instrumented-paloma/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/PALOMA NewsBlur-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/RedReader/build/outputs/apk/debug/PALOMA RedReader-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/instrumented-paloma/uob-timetable-android/uob/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
    # Perfect
    "${PROJECT_DIR}/perfect/AntennaPod/app/build/outputs/apk/free/debug/app-free-debug.apk"
    "${PROJECT_DIR}/perfect/Hillffair/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/materialistic/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/NewsBlur/clients/android/NewsBlur/build/outputs/apk/debug/Perfect NewsBlur-debug.apk"
    "${PROJECT_DIR}/perfect/RedReader/build/outputs/apk/debug/Perfect RedReader-debug.apk"
    "${PROJECT_DIR}/perfect/Travel-Mate/Android/app/build/outputs/apk/debug/app-debug.apk"
    "${PROJECT_DIR}/perfect/uob-timetable-android/uob/uob-timetable/build/outputs/apk/debug/uob-timetable-debug.apk"
)

APKS_DST=(
    # Baseline
    "${PROJECT_DIR}/build/apks/baseline/baseline.de.danoeh.antennapod.debug.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.appteam.nith.hillffair.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.io.github.hidroh.materialistic.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.com.newsblur.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.org.quantumbadger.redreader.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.io.github.project_travel_mate.apk"
    "${PROJECT_DIR}/build/apks/baseline/baseline.com.ak.uobtimetable.apk"
    # Nappa Greedy
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.de.danoeh.antennapod.debug.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.appteam.nith.hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.io.github.hidroh.materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.com.newsblur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.org.quantumbadger.redreader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.io.github.project_travel_mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-greedy/nappa.greedy.com.ak.uobtimetable.apk"
    # Nappa TFPR
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.de.danoeh.antennapod.debug.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.appteam.nith.hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.io.github.hidroh.materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.com.newsblur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.org.quantumbadger.redreader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.io.github.project_travel_mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-nappa-tfpr/nappa.tfpr.com.ak.uobtimetable.apk"
    # PALOMA
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.de.danoeh.antennapod.debug.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.appteam.nith.hillffair.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.io.github.hidroh.materialistic.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.com.newsblur.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.org.quantumbadger.redreader.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.io.github.project_travel_mate.apk"
    "${PROJECT_DIR}/build/apks/instrumented-paloma/paloma.com.ak.uobtimetable.apk"
    # Perfect
    "${PROJECT_DIR}/build/apks/perfect/perfect.de.danoeh.antennapod.debug.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.appteam.nith.hillffair.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.io.github.hidroh.materialistic.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.com.newsblur.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.org.quantumbadger.redreader.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.io.github.project_travel_mate.apk"
    "${PROJECT_DIR}/build/apks/perfect/perfect.com.ak.uobtimetable.apk"
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
