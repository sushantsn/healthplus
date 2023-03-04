Doctor-Appointment-System-REST-API

The aim of the course project is to develop a RESTful API for booking a doctor's appointment. The API should perform the following tasks:

- To return data for all doctors available in the system.
- To be able to search for a doctor according to entered criteria.
- To return data about a free day and time of a selected doctor.
- To record the saved time in the system.
- To update information about a reserved time.
- To cancel a reserved appointment.

For this purpose, a database was developed to store the following data:

- Information for doctors
- Working hours of doctors
- Specializations of doctors
- Doctors' holidays
- Details of reserved hours
- Patient data
- Information on reasons for doctor visits

MS SQL Server was used for the database for the program implementation. The C# programming language was used to implement the API. The development environments used are SQL Server Management Studio and Microsoft Visual Studio 2017.

The developed API can be implemented in the healthcare system as it would offer easy and convenient booking of appointments with different doctors. The API has possible different development directions that can be developed, depending on the needs.

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<p><value></p><p></p>|

GET

Doctor Available Days and Hours

http://localhost:8080/api/doctors/docId/3/date/2023-12-05/NumDays/3/available-days?type=json

- Returns data about the days on which the doctor has free appointments.
- Can be used to check a specific date - in this case pass NumDays = 0.
- When NumDays > 0 it will return N days after the input Date and free hours information.

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p></p><p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :-: | :-: |


GET

Search Doctors

http://localhost:8080/api/doctors?type=json

Search for a doctor by specified criteria.

- If no parameter is passed - all doctors are returned.
- You can search by one or more parameters.

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

**Params**

|<p>DocId</p><p></p>|<p>3</p><p>Integer, must be greater than 0.</p>|
| :-: | :-: |
|<p>CityId</p><p></p>|<p>1</p><p>Integer, Yes greater than 0. Can be taken from list of cities.</p><p></p>|
|<p>SpecId</p><p></p>|<p>79</p><p>Integer, Yes greater than 0. Can be taken from the specialty list.</p><p></p>|
|<p>Eml</p><p></p>|<p>test@abv.bg</p><p>String, Must be in correct mail format.</p><p></p>|
|<p>Tel</p><p></p>|<p></p><p>011111111</p><p>String, Must contain only numbers.</p><p></p>|
|<p>FullName</p><p></p>|<p>Peter</p><p>String, Can be first name, full name, or part of name.</p><p></p>|
|<p>type</p><p></p>|<p>json</p><p></p>|

GET

List All Doctors Paginated

http://localhost:8080/api/doctors/PageNum/1/PageSize/10?type=json

The method returns data for all available doctors in the paging system.

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS


|<p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :- | :- |


POST

Create Booking

http://localhost:8080/api/bookings?type=json

Enters a record in the database with information about the patient and the desired date and time for a doctor's appointment. All parameters must be filled.

The method performs the following checks:

- Cannot save an appointment on a previous date.
- Cannot save a class on a day that is greater than today + max\_bok\_days. I.e. if max\_bok\_days = 30 and today's date is 01.12.2020, then 30.12.2020. is the last day on which an appointment can be reserved. This parameter is set dynamically for each doctor.
- An appointment cannot be reserved on a day when the doctor is not working - in case such a date is passed as an input parameter.
- An appointment cannot be reserved for a time when the doctor is not on shift.
- A reserved class cannot be duplicated.
- On success in Headers.Location, on success a link is returned where information about the reserved time can be obtained.


AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :- | :- |

BODYraw1     {

` `2.     "Fname" : "APIto",

` `3.     "Lname" : "Test",

` `4.     "Tel" : "0881145781",

` `5.     "Eml" : "test12@abv.bg",

` `6.     "AddInfo" : "Testing the API for 3 time. And commas, should work now. (I think so)",

` `7.     "IsFirstTime" : "1",

` `8.     "Gender" : "dk",

` `9.     "ReasonId" : "1",

\10.     "DocId" : "3",

\11.     "BokDate" : "2020-12-12",

\12.     "BokTime" : "08:00"

\13.  

\14. }







PUT

Update Booking

http://localhost:8080/api/bookings?type=json

Updates data for the saved time.

AUTHORIZATIONAPI Key

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :- | :- |

Body Raw

` `1. {

` `2.     "Reference" : "202012121100110",

` `3.     "Fname" : "Proba",

` `4.     "Lname" : "Test",

` `5.     "Tel" : "0881111111",

` `6.     "Eml" : "test5@abv.bg",

` `7.     "AddInfo" : "Testing the API for 549 time. And commas, should work now. (I think so)",

` `8.     "IsFirstTime" : "1",

` `9.     "Gender" : "m",

\10.     "ReasonId" : "2",

\11.     "DocId" : "3",

\12.     "BokDate" : "2020-12-12",

\13.     "BokTime" : "10:00"

\14. }

\15.  

OF

Cancel Booking

http://localhost:8080/api/bookings?type=json

The method cancels a saved time. Cannot cancel a class in the past.

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :- | :- |
BODYraw

"202012120930113"

GET

Get All Reasons

http://localhost:8080/api/bookings/reasons?type=json

Returns data for all reasons for keeping an appointment available in the system.

AUTHORIZATIONAPI Key

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p>type</p><p></p>|<p>json</p><p>String, JSON - returns a JSON result; XML - returns an XML result;</p>|
| :- | :- |

GET

Get Booking Info

http://localhost:8080/api/bookings/202012121130111?type=json

AUTHORIZATIONAPI Key

AUTHORIZATIONAPI Key

|<p>Key</p><p></p>|<p>APIkey</p><p></p>|
| :-: | :-: |
|<p>Value</p><p></p>|<value>|

PARAMS

|<p>type</p><p></p>|json|
| :-: | :-: |

