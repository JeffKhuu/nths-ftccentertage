

# NTHS Robotics (2023-24 Centerstage)
## Terminology
Commonly used terminology can be found below, refer to this section if you are unsure of a particular word used.
* **FTC** - First Tech Challenge  ඞ
* **OpMode (Operation Mode):** *FTC*-Standard sections of code ran during the competitions.
  * **Linear OpMode:** Code ran *linearly*. From start to finish.
  * **Iterative OpMode:** Code made to ran repeatedly. After initial methods are ran, the loop() method is ran continuously until stopped.
> https://gm0.org/en/latest/docs/software/getting-started/linear-opmode-vs-opmode.html See for different types of methods in each type of OpMode.
* **Package:** Java-Version of a folder.
*  **Mecanum Drive Train:** A type of wheel *and* wheel configuration allowing for omnidirectional movement.
> https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html See for more information on Mecanum Drive Trains.


## Project Structure
**Where is the code?** Team code is stored in the *teamcode* package. The full package directory is below under the *Java* folder in the *Team Code* folder:    
`org.inspires.ftc.teamcode`

**Autonomous** `org.inspires.ftc.teamcode.autonomous` The autonomous package stores all the autonomous modes for the 2023-24 season (Centerstage). The modes are organized by the starting position of the robot on the Centerstage field.

**Concepts** `org.inspires.ftc.teamcode.concepts` Work-In-Progress concept OpModes meant for testing and debugging code. Contains advanced concepts such as motor encoding, AprilTag detection.

**Systems** `org.inspires.ftc.teamcode.systems` Re-usable systems meant to encapsulate larger systems and methods of the robot. For example: DriveTrain.java contains logic and methods in order to move the robot along it's Mecanum Drive Train. `move(), setSpeed(), DcMotor leftMotor, DcMotor rightMotor`

## How to Upload Code?
When uploading code wirelessly to the REV control hub, you require 3 things:


- [ ] REV Control Hub connected via a battery
- [ ] Wi-Fi connection to the signal outputted by the REV Control Hub (`24124-RC`)
- [ ] REV Hardware Client & Android Studio

1. Make sure the REV Control Hub is connected to power.
2. Connect to the **Wi-Fi network:** `24124-RC` using the password: `24124NTHS`
3. Ensure you're connected via the **REV Hardware Client**.  If done correctly, at the top of Android Studio, your selected device will say "REV Control Hub".
5. **Download the**  [ADB Wifi Plugin](https://plugins.jetbrains.com/plugin/14969-adb-wi-fi)  **for Android Studio.**  Use File>Settings, then click on the Plugins tab. Make sure you're in the Marketplace, then search for ADB Wi-fi and choose the one by Yury Polek.
6. **Connect the Control Hub to your Computer**. If all goes well, this should be the last time you need to connect with a wire.
7. **Click on the ADB Wi-fi tab**  in the bottom right of Android Studio. There should be an option to connect to the Control Hub. Click it. Unplug your Control Hub. You should remain connected.
8. **To make sure it's working, restart your Control Hub**. A section should appear in the ADB Wi-fi tab called "Previously Connected Devices." When the Control Hub is running again, reconnect to its Wi-Fi network and click the connect button under the device in the tab called "REV Robotics Control Hub." You should reconnect without ever hooking up a wire!