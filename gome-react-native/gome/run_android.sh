echo 'Grabbing files that should have already fucking moved'
react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res/
echo 'Running "react-native run-android"'
react-native run-android
