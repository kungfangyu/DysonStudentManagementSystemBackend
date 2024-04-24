/*
Create UserPrimaryData Table

Stores user details required to create an account, including their Unique ID, name, phone number, personal email, what role they play in the system (staff, student)

Original Author: Billy Peters, 11/03/2024

Modifying Author: Billy Peters 29/03/2024 – Merged StaffMember and Student tables to merge the two user tables into one.
Modifying Author: Billy Peters 16/04/2024 - Split User table into Primary and Secondary Data Tables, to conform to database normalisation requirements (removing null values from tables)
*/
CREATE TABLE `UserPrimaryData` (
`UserID` VARCHAR(8),
`UserType` ENUM('student', 'teacher', 'admin') NOT NUll,
`FirstName` VARCHAR(70) NOT NUll,
`LastName` VARCHAR(70) NOT NUll,
`DOB` DATE NOT NUll,
`PersonalEmail` VARCHAR(254) NOT NUll,
`DysonEmail` VARCHAR(254) NOT NUll,
`Phone` VARCHAR(15) NOT NUll,
PRIMARY KEY(`UserID`)
);

/*
Create User_SecondaryData Table

Stores user details that are not required to create an account, including their id photo, address

Original Author: Billy Peters, 11/03/2024

Modifying Author: Billy Peters 29/03/2024 – Merged StaffMember and Student tables to merge the two user tables into one.
Modifying Author: Billy Peters 16/04/2024 - Split User table into Primary and Secondary Data Tables, to conform to database normalisation requirements (removing null values from tables)
*/
CREATE TABLE `UserSecondaryData` (
`UserID` VARCHAR(8),
`IdPhoto` VARCHAR(256),
`Title` VARCHAR(50),
`MiddleNames` VARCHAR(100),
`Gender` VARCHAR(50),
`Ethnicity` VARCHAR(50),
`Address` VARCHAR(120),
`Postcode` VARCHAR(8),
PRIMARY KEY(`UserID`),
CONSTRAINT `User_SecondaryData_FK_UserID` FOREIGN KEY (`UserID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);



/*
Create UserLogIn Table

Stores student login details, identified by their UniqueID, which references the User table, and their password. 
The user's password is stored separately to prevent access by SELECT* statements on User tables. 

Original Author: Billy Peters, 11/03/2024
Modifying Author: Billy Peters 29/03/2024 – Merged StaffLogIn and StudentLogIn tables to merge the two user tables into one.
*/
CREATE TABLE `UserLogIn` (
`UserID` VARCHAR(8),
`Password` VARCHAR(100), -- Password length is long to allow for encrypted password storage (using something like SHA-256 hashing ( a common password encryption method)
PRIMARY KEY(`UserID`),
CONSTRAINT `UserLogIn_FK_UserID` FOREIGN KEY (`UserID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create UserEmergencyContact Table

Stores the details of a student's emergency contact (a weak entity that depends on a Student). Identified by StudentID (references student table), and firstname and lastname of the emergency contact.

Original Author: Billy Peters, 11/03/2024
Modifying Author: Billy Peters 29/03/2024 – Merged StaffEmergencyContact and StudentEmergencyContact tables to merge the two user tables into one.
*/
CREATE TABLE `UserEmergencyContact` (
`UserID` VARCHAR(8),
`ContactPriority` INT, -- Order in which to contact emergency contacts.
`FirstName` VARCHAR(70),
`LastName` VARCHAR(70),
`Title` VARCHAR(70),
`Email` VARCHAR(254),
`Phone` VARCHAR(15),
`Relation` VARCHAR(20),
`Address` VARCHAR(120),-- Lines of Address should by maked by a comma.
`Postcode` VARCHAR(8),
PRIMARY KEY(`UserID`,`ContactPriority`),
CONSTRAINT `UserEmergencyContact_FK_StudentID` FOREIGN KEY (`UserID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create PreviousQualification Table

Stores the details of a students previous qualifications (e.g. GCSE, A-level, Undergraduate degree)

Original Author: Billy Peters, 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `PreviousQualification` (
`UserID` VARCHAR(8),
`QualificationLevel` VARCHAR(50),
`Subject` VARCHAR(50),
`Grade` VARCHAR(20),
`DateAchieved` DATE,
`Institution` VARCHAR(100),
PRIMARY KEY (`UserID`,`QualificationLevel`,`Subject`),
CONSTRAINT `PreviousQualification_FK_StudentID` FOREIGN KEY (`UserID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create StudentTutor Table

Stores which member of staff a student is tutored by.

Original Author: Billy Peters, 29/03/2024
*/
CREATE TABLE `StudentTutor`(
`StudentID` VARCHAR(8),
`StaffID` VARCHAR(8),
PRIMARY KEY (`StaffID`,`StudentID`),
UNIQUE(`StudentID`),
CONSTRAINT `StudentTutor_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `StudentTutor_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE RESTRICT
);

/*
Create TutorStudentMeeting Table

Stores the details of a student tutor meetings
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID and StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `TutorStudentMeeting` (
`StaffID` VARCHAR(8),
`StudentID` VARCHAR(8),
`MeetingTime` DATETIME,
`Notes` TEXT,
PRIMARY KEY (`StaffID`,`StudentID`,`MeetingTime`),
CONSTRAINT `TutorStudentMeeting_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `TutorStudentMeeting_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create Programme table
Stores programme details, including name, length and the total number of credits
Original Author: Billy Peters 11/03/2024
*/
CREATE TABLE `Programme` (
`ProgrammeID` VARCHAR(12),
`Name` VARCHAR(50),
`StartDate` Date,
`EndDate` Date,
`Description` TEXT,
`TotalCredits` INT,
`isGradesReleased` BOOL,
PRIMARY KEY(`ProgrammeID`)
);

/*
Create ProgrammeStaff table
Stores which staff members are assigned to the programme, including being able to make announcements
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `ProgrammeStaff` (
`ProgrammeID` VARCHAR(12),
`StaffID` VARCHAR(8),
PRIMARY KEY(`ProgrammeID`,`StaffID`),
CONSTRAINT `ProgrammeStaff_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `ProgrammeStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);


/*
Create StudentProgrammeEnrolment table
Stores which students have been enrolled on a programme, the status of their enrolment, and if completed their final grade
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
**/
CREATE TABLE `StudentProgrammeEnrolment` (
`StudentID` VARCHAR(8),
`ProgrammeID` VARCHAR(12),
`Status` ENUM('enrolled','suspended','withdrawn','completed'),
`DateEnrolled` DATE,
`DateCompleted` DATE,
`FinalGrade` FLOAT,
PRIMARY KEY (`StudentID`,`ProgrammeID`),
CONSTRAINT `StudentProgrammeEnrolment_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `StudentProgrammeEnrolment_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE RESTRICT
);


/*
Create ModuleDetails table
Stores module details including name, and how many credits it is worth
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 24/04/2024 Changed name from Module to ModuleDetails, as module is already used in java/spring.
*/
CREATE TABLE `ModuleDetails` (
`ModuleID` VARCHAR(12),
`ModuleName` VARCHAR(100),
`ModulePhoto` VARCHAR(256),
`StartDate` DATE,
`EndDate` DATE,
`ModuleCredits` INT,
PRIMARY KEY(`ModuleID`)
);

/*
Create ProgrammeModules table
Stores which modules are listed as part of a programme
Original Author: Billy Peters 11/03/2024
*/
CREATE TABLE `ProgrammeModules` (
`ModuleID` VARCHAR(12),
`ProgrammeID` VARCHAR(12),
PRIMARY KEY (`ModuleID`,`ProgrammeID`),
CONSTRAINT `ProgrammeModules_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE RESTRICT,
CONSTRAINT `ProgrammeModules_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create ModuleStaff table
Stores which staff are assigned to run a module
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `ModuleStaff` (
`ModuleID` VARCHAR(12),
`StaffID` VARCHAR(8),
PRIMARY KEY(`ModuleID`,`StaffID`),
CONSTRAINT `ModuleStaff_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `ModuleStaff_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create Lesson table
Stores lesson (e.g. lecture, tutorial, practical) details for a module, including lesson start date.
Original Author: Billy Peters 11/03/2024
*/
CREATE TABLE `Lesson` (
`ModuleID` VARCHAR(12),
`LessonID` INT,
`LessonType` ENUM('Lecture','Practical','Tutorial'),
`StartTime` DATETIME,
`EndTime` DATETIME,
`isAttendanceRequired` BOOL,
PRIMARY KEY (`ModuleID`,`LessonID`),
CONSTRAINT `Lesson_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE 
);

/*
Create LessonStaff table
Stores which members of staff are assigned to lead a lesson
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `LessonStaff` (
`ModuleID` VARCHAR(12),
`LessonID` INT,
`StaffID` VARCHAR(8),
PRIMARY KEY (`ModuleID`,`LessonID`,`StaffID`),
CONSTRAINT `LessonStaff_FK_Lesson` FOREIGN KEY (`ModuleID`,`LessonID`) REFERENCES `Lesson`(`ModuleID`,`LessonID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `LessonStaff_FK_Staff` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create StudentLesson table
Stores which students may attend a lesson
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `StudentLessonAllocation` (
`ModuleID` VARCHAR(12),
`LessonID` INT,
`StudentID` VARCHAR(8),
`isAttended` ENUM('true','false','permitted absesence'),
PRIMARY KEY (`ModuleID`,`LessonID`,`StudentID`),
CONSTRAINT `StudentLesson_FK_Lesson` FOREIGN KEY (`ModuleID`,`LessonID`) REFERENCES `Lesson`(`ModuleID`,`LessonID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `StudentLesson_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create StudentModuleGrade table
Stores a students grade for a module, and their overall attendance of the module.
Original Author: Billy Peters 11/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `StudentModuleGrade` (
`StudentID` VARCHAR(8),
`ModuleID` VARCHAR(12),
`Grade` FLOAT, -- Should be calculated from module coursework/exam grades, when module is completed.
`PercentageAttendance` FLOAT, -- Should be calculated from lesson attendances
PRIMARY KEY (`StudentID`,`ModuleID`),
CONSTRAINT `StudentModuleGrade_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE, 
CONSTRAINT `StudentModuleGrade_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE RESTRICT
);

/*
Create Coursework table
Stores the details of each coursework, a weak entity dependant on a module, including the deadline.
Original Author: Billy Peters 11/03/2024
*/
CREATE TABLE `Coursework` (
`ModuleID` VARCHAR(12),
`CourseworkID` INT,
`Description` TEXT,
`Deadline` DATETIME,
`PercentageOfModule` FLOAT,
`isCourseworkPublished` BOOL,
`isGradePublished` BOOL,
PRIMARY KEY (`ModuleID`,`CourseworkID`),
CONSTRAINT `Coursework_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create StudentCourseworkGrade table
Stores student’s coursework, including submitted coursework files, and whether the submission was on time.
Original Author: Billy Peters 24/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `StudentCourseworkGrade` (
`ModuleID` VARCHAR(12),
`CourseworkID` INT,
`StudentID` VARCHAR(8),
`SubmissionFilePath` VARCHAR(256),
`SubmissionTime` DATETIME,
`isOnTime` BOOL, -- True if before Deadline in coursework table, or if before AdjustedDeadline in extension table, false if after.
`Grade` FLOAT,
`Feedback` TEXT,
PRIMARY KEY (`ModuleID`,`CourseworkID`,`StudentID`),
CONSTRAINT `StudentCoursework_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `StudentCoursework_FK_Coursework` FOREIGN KEY (`ModuleID`,`CourseworkID`) REFERENCES `Coursework`(`ModuleID`,`CourseworkID`) ON UPDATE CASCADE ON DELETE CASCADE
);


/*
Create CourseworkExtensionRequest table
Stores extension request details, including the status of the request, and the length of new deadline date if an extension is given.
Original Author: Billy Peters 24/03/2024
*/
CREATE TABLE `CourseworkExtensionRequest` (
`StudentID` VARCHAR(8),
`ModuleID` VARCHAR(12),
`CourseworkID` INT,
`RequestNumber` INT,
`RequestDate` DATETIME,
`RequestReason` TEXT,
`Status` ENUM('Submitted', 'Accepted', 'Rejected'),
`AdjustedDeadline` DATETIME,
PRIMARY KEY (`StudentID`,`ModuleID`,`CourseworkID`,`RequestNumber`),
CONSTRAINT `CourseworkExtensionRequest_FK_Coursework` FOREIGN KEY (`StudentID`,`ModuleID`,`CourseworkID`) REFERENCES `StudentCourseworkGrade`(`StudentID`,`ModuleID`,`CourseworkID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create Exam table
Stores details of an exam, a weak entity dependant on a module.
Original Author: Billy Peters 24/03/2024
*/
CREATE TABLE `Exam` (
`ModuleID` VARCHAR(12),
`ExamID` INT,
`StartTime` DATETIME,
`EndTime` DATETIME,
`PercentageOfModule` FLOAT,
PRIMARY KEY (`ModuleID`,`ExamID`),
CONSTRAINT `Exam_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create StudentExamGrade table
Stores student’s exam results.
Original Author: Billy Peters 24/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StudentID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `StudentExamGrade` (
`ModuleID` VARCHAR(12),
`ExamID` INT,
`StudentID` VARCHAR(8),
`Grade` FLOAT,
PRIMARY KEY (`ModuleID`,`ExamID`,`StudentID`),
CONSTRAINT `StudentExamGrade_FK_Exam` FOREIGN KEY (`ModuleID`,`ExamID`) REFERENCES `Exam`(`ModuleID`,`ExamID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `StudentExamGrade_FK_StudentID` FOREIGN KEY (`StudentID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create ProgrammeAnnouncement table
Stores programme announcements, including who posted the announcement.
Original Author: Billy Peters 24/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/

CREATE TABLE `ProgrammeAnnouncement` (
`ProgrammeID` VARCHAR(12),
`AnnouncementID` INT,
`StaffID` VARCHAR(8),
`Title` TEXT,
`Description` TEXT,
`DatePosted` DATETIME,
PRIMARY KEY (`ProgrammeID`,`AnnouncementID`),
CONSTRAINT `ProgrammeAnnouncement_FK_ProgrammeID` FOREIGN KEY (`ProgrammeID`) REFERENCES `Programme`(`ProgrammeID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `ProgrammeAnnouncement_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE SET NULL
);

/*
Create ModuleAnnouncement table
Stores module announcements, including who posted the announcement.
Original Author: Billy Peters 24/03/2024
Modifying Author: Billy Peters 29/03/2024  - Altered StaffID to reference User table, to reflect the merge of Student and StaffMember tables
*/
CREATE TABLE `ModuleAnnouncement` (
`ModuleID` VARCHAR(12),
`AnnouncementID` INT,
`StaffID` VARCHAR(8),
`Title` TEXT,
`Description` TEXT,
`DatePosted` DATETIME,
PRIMARY KEY (`ModuleID`,`AnnouncementID`),
CONSTRAINT `ModuleAnnouncement_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT `ModuleAnnouncement_FK_StaffID` FOREIGN KEY (`StaffID`) REFERENCES `UserPrimaryData`(`UserID`) ON UPDATE CASCADE ON DELETE SET NULL
);

/*
Create ModuleMaterial table
Stores module materials, including whether the materials are released.
Original Author: Billy Peters 24/03/2024
*/
CREATE TABLE `ModuleMaterial` (
`ModuleID` VARCHAR(12),
`MaterialNumber` INT,
`Title` TEXT,
`Description` TEXT,
`isPublished` BOOL,
PRIMARY KEY (`ModuleID`,`MaterialNumber`),
CONSTRAINT `ModuleMaterial_FK_ModuleID` FOREIGN KEY (`ModuleID`) REFERENCES `ModuleDetails`(`ModuleID`) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
Create ModuleMaterialFile table
Stores module material files, weak entities dependant on module materials, by recording the filepath.
Original Author: Billy Peters 24/03/2024
*/
CREATE TABLE `ModuleMaterialFile` (
`ModuleID` VARCHAR(12),
`MaterialNumber` INT,
`FileNumber` INT,
`FilePath` VARCHAR(256),
PRIMARY KEY(`ModuleID`,`MaterialNumber`,`FileNumber`),
CONSTRAINT `ModuleMaterialFiles_FK_ModuleMaterial` FOREIGN KEY (`ModuleID`,`MaterialNumber`) REFERENCES `ModuleMaterial`(`ModuleID`,`MaterialNumber`) ON UPDATE CASCADE ON DELETE CASCADE
);

