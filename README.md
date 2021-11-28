# FindMeAtm
-- Main purpose of the program is to find the most suitable ATM in a city.

First task is to develop Android program by using Kotlin. What does this program do? Main
purpose of the program is to find the most suitable ATM in a city. Suitable means that the person who
want to use an ATM will find the most time-efficient ATM for withdraw and cash-deposit. Nowadays, bank
applications have a feature that they can show you the nearest atm by only distance from the point.
However, this program will have additional feature which will show the atm that the person will spend less
time by using it. It can be considered as a step for smart city apps. Basically, the algorithm of the program
that it can be imagined that there is some information how many people are in queue for the ATMs (This
info can be handled by using image recognition or any sensor network applications). The program will
compare that which ATM is going to be fast to finish your process in less time rather than using the
nearest one. In this program, there is needed a data set. This data set will be created as a random instead
of real information. Also, the program will have user-friendly interface which can be implemented to any
4
bank apps. In addition, it is trying to use the ability of Kotlin which interoperability with JAVA in this
android project.

# Design Of the Project
![image](https://user-images.githubusercontent.com/68166794/143786516-7cc731a6-a2fa-4a0b-9251-ba76430f884b.png)

The basic design of UI is shown above Figure. It can be simply said that when user starts to app, the
main menu which contains 4 Buttons that are ‘My account’, ‘Cards’, ’Rates’( These will be inactive), ‘Find
Atm’(Our Active Button) appears on the screen. By touching ‘Find Atm’ button, the selection screen
appears. The user is able to select either withdraw money or deposit money. After this selection, Google
Map interface will be seen on the screen. This map contains the location pin of the user and the nearest
available ATM/Bank office. As a last step, one more touching on pin of the logo of the bank will show
distance and time information of the ATM/Bank Office.

# Algorithm of the Program

When the user start the app, and pushing the ‘Find ATM button’ → ‘Withdraw/Deposit’ the below
flowchart shows how to find suitable 3 ATMs.

→ The user location information is getting from GoogleMaps LatLng() function. This function provides
current longitude and latitude information of user.

→ The distance between user and every single atm by using distanceBetween() function which is belong
Location Class that is subclass GoogleMaps.

→ While calculating distance, the below graph shows that how to find minimum distance of 3 ATMs.

→ After finding nearest 3 ATMs, only the time information of these three ATM are calculating to reduce
the compilation time. The below equations show that how the calculations are done.

if (x∗y)- d > 0, T=(x∗y) ,
if (x∗y)- d< 0, T=d where,
x : total person number
y : average time which a person uses ATM
d :distance between user and an ATM
T :Stack Time

→ Lastly, this time information is sorted by using basic swap-sorting algorithm by minimum.

# Testing Data

The testing stage of the application is done by assuming that every 100 meters distance will be equal to 1
minute to reach the ATM. Approximately, one person of ATM usage time is accepted as 3 minutes for
finishing the process. The distribution of person number which is waiting at ATM is assumed that in a
range of 0 and 6. For ATM location, 30 different Bank ATMs locations which are in Budapest are used. For
user location, my phone current location and location is provided by fake GPS apps is used. The results is
shown below.

<img src="https://user-images.githubusercontent.com/68166794/143786793-d50bf81b-7a15-4993-9f86-18a2621e64df.jpeg" width="400">

<img src="https://user-images.githubusercontent.com/68166794/143786788-0f782de6-cf7e-4960-b0b1-37019a12c156.jpeg" width="400">

<img src="https://user-images.githubusercontent.com/68166794/143786789-f8291544-1bff-42fd-bf52-9a56077188f7.jpeg" width="400">

<img src="https://user-images.githubusercontent.com/68166794/143786791-c9f4c0b4-79a2-4ea8-b544-6d4453b1132d.jpeg" width="400">

<img src="https://user-images.githubusercontent.com/68166794/143786792-33d9da50-1a63-4b34-a5aa-e19c924f8035.jpeg" width="400">




