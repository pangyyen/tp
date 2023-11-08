---
layout: page
title: User Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

# 1. Introduction 🎯

**CareCentral** is built specifically for medical staffs — including doctors, nurses, and hospital staff aged between 25-60 years. Optimized for fast typists, it streamlines the management of patients' medical journeys.

<div style="background-color: #DFF0D8; padding: 10px; margin-bottom: 10px; border-left: 5px solid #4CAE4C;">
  <em>Note: You can download our latest release from <a href="https://github.com/AY2324S1-CS2103T-F08-1/tp/releases/tag/v1.3.1" target="_blank">here</a>.</em>
</div>

# 2. Quick Start Guide ⚡️
Here's how to get started with CareCentral quickly:

1. Ensure you have Java 11 installed in your Computer.
2. Download the latest `carecentral.jar` from [here](https://github.com/AY2324S1-CS2103T-F08-1/tp/releases/tag/v1.3.1) if you have not downloaded it yet.
3. Copy the file to the folder you want to use as the home folder for CareCentral.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)
5. Alternatively, you can also go to the folder where the `carecentral.jar` is located and type `java -jar carecentral.jar` in the command box to start the app.
6. For Mac users who are unable to open the file or encountering this issue, follow this [guide](https://nus-cs2103-ay2223s2.github.io/website/admin/programmingLanguages.html).
![Error](images/mac_issue.png)
7. Type the command in the command box and press Enter to execute it.
   e.g. typing `help` and pressing Enter will open the help window.
   Some example commands you can try:
   
   * `add-patient n/John Doe ic/S1234567A p/98765432 e/johnd@example.com a/25 t/Diabetic`
   * `list-patients`
   * `delete-patient 1`
   * `exit`

<div style="background-color: #E3F2FD; padding: 10px; margin-top: 10px; border-left: 5px solid #64B5F6;">
  <strong>🌟 Congratulations on starting with CareCentral!</strong> The next sections will guide you through more features to fully utilize the app.
</div>

# 3. GUI components 🖥️

## 3.1. Main screen 🌟
<img width="600" height="400" src="images/UiWithDescriptions.png" alt="Main Screen.">

## 3.2 Sidebar 🗂️
The sidebar contains the following tabs:
* Patients
* Medical History
* Appointments

Users can switch between the tabs by clicking on the respective tabs, using the switch command (see here) or using Ctrl+T.

## 3.3 Panel 📋
There are 3 panels in the main screen:
* Patient List Panel
* Medical History Panel
* Appointment Panel

Users can switch between the panels by switching between the respective tabs by using CTRL + T.

## 3.4 Result Display Box 📬
The result display box displays the result of the command executed.

## 3.5 Command Box ⌨️
The command box is where the user can type in commands to be executed.

# 4. Common Parameters 🔍
Below is a table of parameters you'll commonly use in CareCentral, along with their explanations and constraints:

| Parameters             | Explanation                       | Constraints                                                                                                                                                 |
|------------------------|-----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `n/NAME`               | Full name of the patient          | Must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `p/PHONE_NUMBER`       | Phone number of the patient       | Must be **entirely numeric** and exactly 8 digits long                                                                                                      |
| `ic/NRIC`              | NRIC of the patient               | Must be **entirely alphanumeric** and follow Singapore NRIC format. More details [here](https://en.wikipedia.org/wiki/National_Registration_Identity_Card). |
| `a/AGE`                | Age of the patient                | Must be a positive integer                                                                                                                                  |
| `e/EMAIL`              | Email of the patient              | Must be a valid email address                                                                                                                               |
| `[t/TAG]…`             | Tags for patient categorization   | Must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `d/DATE`               | Date of appointment or event      | Must be in the format YYYY-MM-DD                                                                                                                            |
| `t/TIME`               | Time of appointment or event      | Must be in the format HH:MM (24-hour format)                                                                                                                |
| `pi/PATIENT_INDEX`     | Index for referencing a patient   | Must be a positive integer                                                                                                                                  |
| `mc/MEDICAL_CONDITION` | Medical condition of the patient  | Must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `t/TREATMENT`          | Treatment for a medical condition | Must only contain alphanumeric characters and spaces, and it should not be blank                                                                            |
| `mn/MEDICATION_NAME`   | Name of the medication prescribed | Must only contain alphanumeric characters, and it should not be blank                                                                                       |

# 5. Glossary 📚
The following terms are used throughout the CareCentral user guide:

| Word           | Meaning                                                                                                                                                  |
|----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CLI`          | **Command Line Interface** - A text-based interface used for entering commands. In CareCentral, you'll use the CLI to input your actions quickly.        |
| `GUI`          | **Graphical User Interface** - A visual interface with graphical elements. CareCentral's GUI is designed for ease of navigation and information display. |
| `NRIC`         | **National Registration Identity Card** number - A unique identifier for Singaporean residents. CareCentral uses the NRIC to ensure patient uniqueness.  |
| `Prescription` | **Medicine prescribed by a medical professional** - CareCentral allows you to manage and track prescriptions for each patient.                           |

# 6. Features ⭐️
<div style="background-color: #f9f9f9; border-left: 5px solid #009688; padding: 5px; margin-bottom: 10px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
<p style="color: #009688; font-size: 20px;">:information_source: Notes about the command format:</p>
    <ul>
        <li>Words in <code>UPPER_CASE</code> are the parameters to be supplied by the user.<br>
            For example, in <code>add n/NAME</code>, <code>NAME</code> is a parameter which can be used as <code>add n/John Doe</code>.</li>
        <li><strong>Optional items</strong> are in square brackets.<br>
            For example, <code>n/NAME [t/TAG]</code> can be used as <code>n/John Doe t/friend</code> or as <code>n/John Doe</code>.</li>
        <li><strong>Repeating items</strong> marked with <code>…</code> can be used multiple times.<br>
            For example, <code>[t/TAG]…</code> can be used as <code>t/malaria</code>, <code>t/asthma t/malaria</code> or not at all.</li>
        <li><strong>Parameters can be in any order.</strong><br>
            For instance, <code>n/NAME p/PHONE_NUMBER</code> is also acceptable as <code>p/PHONE_NUMBER n/NAME</code>.</li>
        <li><strong>Extraneous parameters</strong> for commands that do not require them such as <code>help</code> <code>exit</code> and <code>clear</code> will be ignored.<br>
            For instance, <code>help 123</code> will be interpreted as <code>help</code>.</li>
    </ul>
    <p style="color: #d9534f;"><strong>:warning: Important:</strong> The <code>clear</code> command will remove all data from the .json file and the action <strong>cannot be undone</strong>. Please use with caution.</p>
    <p><strong>Tip for PDF users:</strong> Be mindful when copying and pasting commands from the PDF as spaces around line breaks may be lost.</p>
</div>


## 6.1. Patients Related Features 🚑

### 6.1.1. Create Patient Record 📝

**What it does:**  
This feature lets you add a brand-new patient record into the CareCentral system, keeping track of all the essential details for each patient.

**Command Format:**  
Type the command below to create a new patient entry: <br>
`add-patient n/NAME ic/NRIC a/AGE p/PHONE_NUMBER e/email [t/TAG]…`

**Example Commands:**
Here's an example to add a patient named John Doe: <br>
`add-patient n/John Doe ic/S0123456A a/45 p/12341234 e/johndoe@example.com`

**Parameters:** <br>
Let's break down what each part of the command means:

| Parameters       | Explanation                      | Constraints                                                                                                                                                  |
|------------------|----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `n/NAME`         | Patient's full name.             | Names must be clear and simple—only the good old ABCs and 123s and spaces, please. No fancy symbols!                                                         |
| `ic/NRIC`        | Unique identity card number.     | The NRIC must be alphanumeric, and following the Singapore's standard. [Learn more here](https://en.wikipedia.org/wiki/National_Registration_Identity_Card). |
| `a/AGE`          | How many candles on their cake?  | Only whole numbers here—we can't have half an age, can we?                                                                                                   |
| `p/PHONE_NUMBER` | Ring, ring! What's their number? | Make sure it's a number we can actually call: 8 digits, nothing more, nothing less.                                                                          |
| `e/email`        | Got mail? Their email address.   | No typos allowed here—let's keep emails professional and valid.                                                                                              |
| `[t/TAG]...`     | What's their story? Any tags?    | Tags should be simple and to the point. Add as many as you like to paint the full picture.                                                                   |

---
### 6.1.2. List Patients 📋

**What it does:**  
Ready to see your whole roster of patients? This command gives you the big picture, listing out all patients currently saved in the CareCentral system.

**Command Format:**  
Just type this simple command to get the full list: <br>
`list-patients`

---

### 6.1.3. Edit Patient Record 📝

**What it does:** <br>
Edits existing patient information at the specified `PATIENT_INDEX` in the system.

**Command Format:** <br>
`edit-patient PATIENT_INDEX [n/NAME] [ic/NRIC] [a/AGE] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…`

**Example Commands:** <br>
`edit-patient 5 n/John Doe ic/S0123456A a/45 p/12341234 e/johndoe@example.com t/critical`<br>
*This example edits the patient record at **index 5** and update the name to **John Doe**, NRIC to **S0123456A**, age to **45**, phone number to **12341234**, and email to **johndoe@example.com**. It also sets the patient tag to **critical**.*

<div style="background-color: #cce7ff; padding: 10px; margin-bottom: 10px; border-left: 5px solid #3385ff;">
  <strong>:scroll: Additional Info<br></strong>
  Editing patient tags will override all the patient's existing tags. <br>
  e.g. <code>edit-patient 5 t/critical</code> will remove all the patient's existing tags and replace it with <code>critical</code>.
</div>

**Parameters:** <br>
Let's break down what each part of the command means:

| Parameters         | Explanation                                                    | Constraints                                                               |
|--------------------|----------------------------------------------------------------|---------------------------------------------------------------------------|
| `PATIENT_INDEX`    | The index number of the displayed patient in the patients tab. | Must be a positive integer.                                               |
| `[n/NAME]`         | The patient's full name.                                       | Should contain only alphanumeric characters and spaces, and not be blank. |
| `[p/PHONE_NUMBER]` | The patient's contact number.                                  | Must be numeric and exactly 8 digits long.                                |
| `[ic/NRIC]`        | The patient's National Registration Identity Card number.      | Must be alphanumeric following the Singapore NRIC format.                 |
| `[a/AGE]`          | The patient's age.                                             | Must be a positive integer.                                               |
| `[e/EMAIL]`        | The patient's email address.                                   | Should be a valid email format.                                           |
| `[t/TAG]...`       | Tags related to the patient.                                   | Should contain only alphanumeric characters and spaces, and not be blank. |

---

### 6.1.4. Delete Patient Record 🗑️

**What it does** <br>
Removes a patient's record from the system. The specific patient is found by the `PATIENT_INDEX` as shown in the list from `list-patients`. **Be aware: This action will permanently erase the patient's record, including all related appointments and medical history.**

**Command Format** <br>
`delete-patient PATIENT_INDEX`

**Example Commands** <br>
`delete-patient 2`<br>
*In this example, `delete-patient 2` will delete the patient record at index 2 from the list of patients. <strong>Make sure to confirm the index number before proceeding to prevent accidental deletion.</strong>*

**Parameters** <br>
Let's break down what each part of the command means:

| Parameters      | Explanation                                                      | Constraints                                                      |
|-----------------|------------------------------------------------------------------|------------------------------------------------------------------|
| `PATIENT_INDEX` | The position number of the patient in the displayed patient list | Must be a positive integer corresponding to a patient's listing. |


---

### 6.1.5. Find Patient Record 🔎

**What it does** <br>
Helps you to locate a patient's record in the system by searching for a keyword. The search functionality is designed to match complete or starting fragments of the name. For instance, searching for `John` will show `John Doe` but not `Johnny`.

**Command Format** <br>
`find KEYWORD`

**Example Commands** <br>
`find John Doe` <br>
In this example, typing `find John Doe` in the command box will fetch the record for any patient named `John Doe`.

**Parameters** <br>
Let's break down what each part of the command means:

| Parameters | Explanation                                                        | Constraints      |
|------------|--------------------------------------------------------------------|------------------|
| `KEYWORD`  | The name or part of the name you're using to search for a patient. | Must be a string |

---

## 6.2 Appointments Related Features 📅

<div style="background-color: #fff3e0; padding: 5px; border-left: 5px solid #ffa726;">
  <strong>📝 Notes from the Developers<br></strong>
  We allow appointment dates to be of past and future dates. This allows medical staff to add/reschedule appointments that have already been scheduled in the past.
  The purpose of appointment dates is to keep track of when the patient visited the clinic and to manage the patient's future appointments with the clinic.
</div>

### 6.2.1 Add Appointment 📝
**What it does:**
Schedules a new appointment for a patient.

**Command Format:**
`add-appt PATIENT_INDEX d/DATE t/TIME`

**Example Commands:**
`add-appt 1 d/2023-10-01 t/14:00`

**Parameters:**

| Parameters           | Explanation                                        | Constraints                                     |
|----------------------|----------------------------------------------------|-------------------------------------------------|
| `PATIENT_INDEX`      | index of the patient in the displayed patient list | must be a positive integer                      |
| `d/DATE`             | date of the appointment                            | must be in the format YYYY-MM-DD                |
| `t/TIME`             | time of the appointment                            | must be in the format HH:MM (24-hour format)    |


---

### 6.2.2 List All Appointments 📋
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
This command should only be used when there is patient(s) in the patient list. <br>
    e.g. <code>list-appointments 1</code> will display the appointments(s) of the patient with index 1. <br>
    If there is no patient in the patient list, you will have to add a patient first before using this command.
</div>

**What it does:**
Displays all appointments of the patient.

**Command Format:**
`list-appointments PATIENT_INDEX`

**Example Commands:**
`list-appointments 1`

**Parameters:**

| Parameters           | Explanation                                        | Constraints                                     |
|----------------------|----------------------------------------------------|-------------------------------------------------|
| `PATIENT_INDEX`      | index of the patient in the displayed patient list | must be a positive integer                      |


---

### 6.2.3 Edit Appointment 📝
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-appointments PATIENT_INDEX</code>
</div>

**What it does:**
Edits **existing** appointment details. The appointment to be edited is identified by the index number shown in the displayed list of appointments by `list-appointments`. 

**Command Format:**
`edit-appt APPOINTMENT_INDEX pi/patient-index [d/DATE] [t/TIME]`

**Example Commands:**
`edit-appt 2 pi/7 d/2023-10-05 t/16:00`

<div style="background-color: #cce7ff; padding: 10px; margin-bottom: 10px; border-left: 3px solid #3385ff;">
  <strong>:scroll: Additional Info<br></strong>
1. You can only edit the appointments of the patient that is currently being displayed. <br>
2. e.g. <code>list-appointments 1</code> will display the appointments of the patient with index 1. <br>
3. <code>edit-appt 2 pi/1 d/2023-10-05 t/16:00</code> will edit the <strong>second</strong> appointment of the patient with index 1. <br>
4. If you want to edit the appointments of another patient, you will have to use <code>list-appointments PATIENT_INDEX</code> to display the appointments of the patient you want to edit. <br>
5. You can refer to the patients tab for the patient index.
</div>

**Parameters:**

| Parameters          | Explanation                                                                                           | Constraints                                  |
|---------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------|
| `APPOINTMENT_INDEX` | index of the appointment in the displayed appointment list                                            | must be a positive integer                   |
| `pi/PATIENT_INDEX`  | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer                   |
| `[d/DATE]`          | date of the appointment                                                                               | must be in the format YYYY-MM-DD             |
| `[t/TIME]`          | time of the appointment                                                                               | must be in the format HH:MM (24-hour format) |


---

### 6.2.4 Delete Appointment 🗑️
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-appointments PATIENT_INDEX</code>
</div>

**What it does:**
Removes an appointment from the system. The appointment to be deleted is identified by the index number shown in the displayed list of appointments by `list-appointments`.

**Command Format:**
`delete-appt APPOINTMENT_INDEX pi/patient-index`

**Example Commands:**
`delete-appt 3 pi/2`

**Parameters:**

| Parameters          | Explanation                                                                                           | Constraints                |
|---------------------|-------------------------------------------------------------------------------------------------------|----------------------------|
| `APPOINTMENT_INDEX` | index of the appointment in the displayed appointment list                                            | must be a positive integer |
| `pi/PATIENT_INDEX`  | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer |


---

### 6.2.5 Add Prescription 📝💊
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-appointments PATIENT_INDEX</code>
</div>

**What it does:**
Adds a prescription to a patient's appointment. The appointment to be edited is identified by the index number shown in the displayed list of appointments by `list-appointments`.

**Command Format:**
`add-prescription APPOINTMENT_INDEX pi/PATIENT_INDEX mn/MEDICATION_NAME...`

**Example Commands:**
`add-prescription 1 pi/1 mn/Paracetamol mn/Albuterol`

**Parameters:**

| Parameters           | Explanation                                                                                           | Constraints                                                                                         |
|----------------------|-------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| `APPOINTMENT_INDEX`  | index of the appointment in the displayed appointment list                                            | must be a positive integer                                                                          |
| `pi/PATIENT_INDEX`   | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer                                                                          |
| `mn/MEDICATION_NAME` | name of the medication                                                                                | must only contain alphanumeric characters, and it should not be blank, can have multiple medication |

---

### 6.2.6 Edit Prescription 📝💊
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-appointments PATIENT_INDEX</code>
</div>

**What it does:**
Edits a prescription of a patient. The prescription to be edited is identified by the index number shown in the displayed list of appointments by `list-appointments`.

**Command Format:**
`edit-prescription APPOINTMENT_INDEX pi/PATIENT_INDEX mn/MEDICATION_NAME...`

**Example Commands:**
`edit-prescription 1 pi/1 mn/Panadol mn/Paracetamol mn/Albuterol`

<div style="background-color: #cce7ff; padding: 10px; margin-bottom: 10px; border-left: 3px solid #3385ff;">
  <strong>:scroll: Additional Info<br></strong>
1. You can only edit the prescription of the appointments that are currently being displayed. <br>
2. e.g. <code>list-appointments 1</code> will display the appointments of the patient with index 1. <br>
3. <code>edit-prescription 2 pi/1 mn/Paracetamol mn/Albuterol</code> will edit the <strong>second</strong> appointment's prescription of the patient with index 1. <br>
4. If you want to edit the prescription of another patient, you will have to use <code>list-appointments PATIENT_INDEX</code> to display the appointments of the patient you want to edit. <br>
5. You can refer to the patients tab for the patient index. <br>
6. Editing prescription will override all the appointment's existing prescription. <br>
  e.g. <code>edit-prescription 2 pi/1 mn/Paracetamol mn/Albuterol</code> will remove all the appointment's existing prescription and replace it with <code>Paracetamol</code> and <code>Albuterol</code>.
</div>

**Parameters:**

| Parameters           | Explanation                                                                                           | Constraints                                                                                         |
|----------------------|-------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| `APPOINTMENT_INDEX`  | index of the appointment in the displayed appointment list                                            | must be a positive integer                                                                          |
| `pi/PATIENT_INDEX`   | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer                                                                          |
| `mn/MEDICATION_NAME` | name of the medication                                                                                | must only contain alphanumeric characters, and it should not be blank, can have multiple medication |

### 6.2.7 Delete Prescription
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-appointments PATIENT_INDEX</code>
</div>

**What it does:**
Removes a prescription from the system. The appointment to be deleted is identified by the index number shown in the displayed list of appointments by `list-appointments`.

**Command Format:**
`delete-prescription APPOINTMENT_INDEX pi/PATIENT_INDEX`

**Example Commands:**
`delete-prescription 1 pi/1`

**Parameters:**

| Parameters          | Explanation                                                                                           | Constraints                |
|---------------------|-------------------------------------------------------------------------------------------------------|----------------------------|
| `APPOINTMENT_INDEX` | index of the appointment in the displayed appointment list                                            | must be a positive integer |
| `pi/PATIENT_INDEX`  | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer |

---

## 6.3 Medical History Related Features

### 6.3.1 Add Medical History

**What it does:**
Adds a medical history to a patient record.

**Command Format:**
`add-medical-history PATIENT_INDEX d/DATE mc/MEDICAL_CONDITION t/TREATMENT`

**Example Commands:**
`add-medical-history 1 d/2023-10-01 mc/asthma t/ventolin`

<div style="background-color: #ffffcc; padding: 3px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note<br></strong>
  If there is no treatment available for the medical condition, you can put None for the treatment.
<br>
  e.g. <code>add-medical-history 1 d/2023-10-01 mc/Cancer t/None</code>
<br>
<br>
For the date, we allow it to be in the past, up until today's date, but not future dates.
</div>

**Parameter**

| Parameters             | Explanation                                                                                           | Constraints                                                                      |
|------------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `PATIENT_INDEX`        | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer                                                       |
| `d/DATE`               | date of the medical history                                                                           | must be in the format YYYY-MM-DD                                                 |
| `mc/MEDICAL_CONDITION` | medical condition                                                                                     | must only contain alphanumeric characters and spaces, and it should not be blank |
| `t/TREATMENT`          | treatment                                                                                             | must only contain alphanumeric characters and spaces, and it should not be blank |

---

### 6.3.2 List Medical History
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
This command should only be used when there is patient(s) in the patient list. <br>
    e.g. <code>list-medical-history 1</code> will display the medical history of the patient with index 1. <br>
    If there is no patient in the patient list, you will have to add a patient first before using this command.
</div>

**What it does:**
Lists the medical history of a patient.

**Command Format:**
`list-medical-history PATIENT_INDEX`

**Example Commands:**
`list-medical-history 1`

**Parameters:**

| Parameters      | Explanation                                                                                           | Constraints                |
|-----------------|-------------------------------------------------------------------------------------------------------|----------------------------|
| `PATIENT_INDEX` | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer |

---

### 6.3.3 Edit Medical History
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-medical-history PATIENT_INDEX</code>
</div>

**What it does:**
Edits a medical history of a patient. The medical history to be edited is identified by the index number shown in the displayed list of medical history by `list-medical-history`.

**Command Format:**
`edit-medical-history MEDICAL_HISTORY_INDEX pi/PATIENT_INDEX [d/DATE] [mc/MEDICAL_CONDITION] [t/TREATMENT]`

**Example Commands:**
`edit-medical-history 1 pi/1 d/2023-10-01 mc/asthma t/Levabuterol`

<div style="background-color: #cce7ff; padding: 10px; margin-bottom: 10px; border-left: 3px solid #3385ff;">
  <strong>:scroll: Additional Info<br></strong>
1. You can only edit the medical history of the patient that is currently being displayed. <br>
2. e.g. <code>list-medical-history 1</code> will display the medical history of the patient with index 1. <br>
3. <code>edit-medical-history 2 pi/1 d/2023-10-01 mc/asthma t/Levabuterol</code> will edit the <strong>second</strong> medical history of the patient with index 1. <br>
4. If you want to edit the medical history of another patient, you will have to use <code>list-medical-history PATIENT_INDEX</code> to display the medical history of the patient you want to edit. <br>
5. You can refer to the patients tab for the patient index.
</div>

**Parameters:**

| Parameters               | Explanation                                                                                           | Constraints                                                                      |
|--------------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `MEDICAL_HISTOY_INDEX`   | index of the medical history in the displayed medical history list                                    | must be a positive integer                                                       |
| `pi/PATIENT_INDEX`       | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer                                                       |
| `[d/DATE]`               | date of the medical history                                                                           | must be in the format YYYY-MM-DD. Can only be dates earlier than today or today  |
| `[mc/MEDICAL_CONDITION]` | medical condition                                                                                     | must only contain alphanumeric characters and spaces, and it should not be blank |
| `[t/TREATMENT]`          | treatment                                                                                             | must only contain alphanumeric characters and spaces, and it should not be blank |

---

### 6.3.4 Delete Medical History
<div style="background-color: #ffffcc; padding: 10px; border-left: 3px solid #ffeb3b; margin-bottom: 10px;">
  <strong>:warning: Things To Note <br></strong>
  This command should only be used after <code>list-medical-history PATIENT_INDEX</code>
</div>

**What it does:**
Deletes a medical history of a patient. The medical history to be deleted is identified by the index number shown in the displayed list of medical history by `list-medical-history`.

**Command Format:**
`delete-medical-history MEDICAL_HISTORY_INDEX [pi/PATIENT_INDEX]`

**Example Commands:**
`delete-medical-history 1 pi/1`

**Parameters:**

| Parameters              | Explanation                                                                                           | Constraints                |
|-------------------------|-------------------------------------------------------------------------------------------------------|----------------------------|
| `MEDICAL_HISTORY_INDEX` | index of the medical history in the displayed medical history list                                    | must be a positive integer |
| `pi/PATIENT_INDEX`      | Index of patient in the patient list. Can refer to patients tab to find out about the patient's index | must be a positive integer |


---

## 6.3 System Related Features
### 6.3.1 Switch Tabs

**What it does:**
Switches between the different tabs in the sidebar.

**Command Format:**
`switch TAB_NUMBER`

**Example Command:**
`switch 1`

**Parameters:**

| Parameters   | Explanation                                                                                         | Constraints                                                                      |
|--------------|-----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `TAB_NUMBER` | The target tab to switch to.<br/>1: Patients Tab<br/>2: Medical History Tab<br/>3: Appointments Tab | must only contain alphanumeric characters and spaces, and it should not be blank |


---


# 7. Command Summary

| Action                     | Format                                                                                                      | Examples                                                                    |
|----------------------------|-------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| **Add Patient**            | `add-patient n/NAME ic/NRIC a/AGE p/PHONE_NUMBER e/email [t/TAG]…`                                          | `add-patient n/John Doe ic/S0123456A a/45 p/12341234 e/johndoe@example.com` |
| **List Patients**          | `list-patients`                                                                                             | `list-patients`                                                             |
| **Edit Patient**           | `edit-patient PATIENT_INDEX [n/NAME] [ic/NRIC] [a/AGE] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…`                 | `edit-patient 5 n/John Doe ic/S0123456A a/45 p/12341234`                    |
| **Delete Patient**         | `delete-patient PATIENT_INDEX`                                                                              | `delete-patient 2`                                                          |
| **Find Patient**           | `find KEYWORD`                                                                                              | `find John Doe`                                                             |
| **Add Appointment**        | `add-appt PATIENT_INDEX d/DATE t/TIME`                                                                      | `add-appt 1 d/2023-10-01 t/14:00`                                           |
| **List Appointments**      | `list-appointments PATIENT_INDEX`                                                                           | `list-appointments 1`                                                       |
| **Edit Appointment**       | `edit-appt APPOINTMENT_INDEX pi/patient-index [d/DATE] [t/TIME]`                                            | `edit-appt 2 pi/7 d/2023-10-05 t/16:00`                                     |
| **Delete Appointment**     | `delete-appt APPOINTMENT_INDEX pi/patient-index`                                                            | `delete-appt 3 pi/2`                                                        |
| **Add Prescription**       | `add-prescription APPOINTMENT_INDEX pi/PATIENT_INDEX mn/MEDICATION_NAME...`                                 | `add-prescription 1 pi/1 mn/Panadol`                                        | 
| **Delete Prescription**    | `delete-prescription APPOINTMENT_INDEX pi/PATIENT_INDEX`                                                    | `delete-prescription 1 pi/1`                                                |
| **Add Medical History**    | `add-medical-history PATIENT_INDEX d/DATE [mc/MEDICAL_CONDITION t/TREATMENT`                                | `add-medical-history 1 d/2023-10-01 mc/asthma t/ventolin`                   |
| **List Medical History**   | `list-medical-history PATIENT_INDEX`                                                                        | `list-medical-history 1`                                                    |
| **Edit Medical History**   | `edit-medical-history MEDICAL_HISTORY_INDEX pi/PATIENT_INDEX [d/DATE] [mc/MEDICAL_CONDITION] [t/TREATMENT]` | `edit-medical-history 1 pi/1 mc/asthma t/ventolin`                          |
| **Delete Medical History** | `delete-medical-history MEDICAL_HISTORY_INDEX [pi/PATIENT_INDEX]`                                           | `delete-medical-history 1 pi/1`                                             |
