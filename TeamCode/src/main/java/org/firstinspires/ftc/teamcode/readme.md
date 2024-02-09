
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
**Where is the code?**  
Team code is stored in the *teamcode* package. The full package directory is below under the *Java* folder in the *Team Code* folder:  
`org.inspires.ftc.teamcode`

**Autonomous** `org.inspires.ftc.teamcode.autonomous`  
The autonomous package stores all the autonomous modes for the 2023-24 season (Centerstage). The modes are organized by the starting position of the robot on the Centerstage field.

**Concepts** `org.inspires.ftc.teamcode.concepts`  
Work-In-Progress concept OpModes meant for testing and debugging code. Contains advanced concepts such as motor encoding, AprilTag detection.

**Systems** `org.inspires.ftc.teamcode.systems`  
Re-usable systems meant to encapsulate larger systems and methods of the robot. For example: DriveTrain.java contains logic and methods in order to move the robot along it's Mecanum Drive Train. `move(), setSpeed(), DcMotor leftMotor, DcMotor rightMotor`

## How to Upload Code?
When uploading code wirelessly to the REV control hub, you require 3 things:


- [ ] REV Control Hub connected via a battery
- [ ] Wi-Fi connection to the signal outputted by the REV Control Hub (`24124-RC`)
- [ ] REV Hardware Client & Android Studio

1. Make sure the REV Control Hub is connected to power.
2. Connect to the Wi-Fi network: `24124-RC` using the password: `24124NTHS`
3. Ensure you're connected via the REV Hardware Client.
4. If done correctly, at the top of Android Studio, your selected device will say "REV Control Hub". Press the "Run [▶]" button to begin uploading code.