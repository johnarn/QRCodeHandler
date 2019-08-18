# QRCodeHandler

This projects aims to be a simple QR Code handler. QRCodeHandler can generate, scan or import a qr code and show the text that was made of. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

What things you need to install the software and how to install them. Add the code below into your gradle app file.

```
implementation 'com.google.android.gms:play-services-vision:x.y.z'
implementation 'androidmads.library.qrgenearator:QRGenearator:x.y.z'
implementation 'com.karumi:dexter:x.y.z'

```
Change x,y,z with the latest version of the above libraries at the time.

## Installing

Set up your device as follows:

1. Connect your device to your development machine with a USB cable. If you are developing on Windows, you might need to install the appropriate USB driver for your device.

2. Enable **USB debugging** in the **Developer options** as follows:
     
   * Open the **Settings** app.
   
   * (Only on Anrdoid 8.0 or higher) Select **System**.
   
   * Scroll to the bottom and select **About phone**.
   
   * Scroll to the bottom ad tap **Build number** 7 times.
   
   * Return to the previous screen to find **Developer options** near the bottom.
      
   * Open **Developer options**, and then scroll down to find and enable **USB debugging**.
   
 3. Run the app on your device as follows:
    
    * In Android Studio, click the **app** module in the **Project** window and then select **Run > Run**.
    
    * In the **Select Deployment Target** window, select your device, and click **OK**.
    
 Android Studio installs the app on your connected device and starts it.



## License
```
The MIT License (MIT)

Copyright (c) 2019 AndroidMad / Mushtaq M A

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
