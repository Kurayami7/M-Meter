# Melody-Meter
A mobile and pervasive computing project delivering a community-driven music rating application

## How To Get It Up & Running:
1. Sign up into TMDB and head over to their API page: https://www.themoviedb.org/settings/api
2. Register for an API and navigate into core/data/remote/build.gradle. Look carefully at how the build is set up.
3. Navigate into local.properties and set the 'API_KEY="XYZ" ## Change this to your own API key' to the API you received after registration.
4. Build the project and run it. 

## How To Use The Android Debug Bridge (adb) 
1. Locate the adb executable
The adb executable is located in the platform-tools directory of your Android SDK. In your case, it is located at:

C:\Users\<your-username>\AppData\Local\Android\Sdk\platform-tools

2. Add adb to your System Path
For Windows:

* Open Environment Variables:

* Right-click on This PC or Computer on your desktop or in File Explorer.
* Select Properties.
* Click on Advanced system settings.
* In the System Properties window, click on the Environment Variables button.
* Edit the Path variable:

* In the Environment Variables window, find the Path variable in the System variables section and select it.
* Click on the Edit button.
* In the Edit Environment Variable window, click on New and add the path to the platform-tools directory:

* C:\Users\<your-username>\AppData\Local\Android\Sdk\platform-tools

* Click OK to close all the windows.
* Verify the setup:

### Open a new Command Prompt window.
Type adb and press Enter. You should see a list of adb commands if everything is set up correctly.