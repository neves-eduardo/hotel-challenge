# The Challenge
You have been assigned to develop a highly scalable hotel reservation system, capable of handling a large volume of simultaneous accesses, which will allow users to search, compare and book hotel rooms efficiently.

## Requirements

Hotel Search: Users should be able to search for hotels based on criteria such as destination, check-in and check-out dates, number of rooms and number of guests.

Comparison of Options: Users should be able to compare different hotel options based on criteria such as price, location, amenities and reviews from other users.

Room Reservation: Users should be able to select a hotel and book rooms for the desired dates by entering information such as name, contact and payment details.

Notification System: Use a notification system to confirm check-in/check-out processing with the appropriate reservation confirmation status.



## Rating criteria

Complete and accurate implementation of functional and technical requirements, along with their modeling and diagram.

Efficiency and scalability of the system in managing a large volume of hotels, reservations and notifications.

Ensuring reliable delivery of notifications, even in the event of temporary system failures.

Correct data persistence, justify the reason for choosing the database.

Implement a comprehensive log and metrics strategy to monitor system performance and health in real-time

# My Solution
![DesafioHotel drawio](https://github.com/neves-eduardo/hotel-challenge/assets/39205974/f0ce4d38-c5af-45c1-a341-a4dc1cc29c2e)

## Running the Project
### Build the projects
#### 1. In the project directory, run the following:
 cd .\hotel-service\
./gradlew clean build
 cd .\reservation-service\
./gradlew clean build

#### 2. Run the docker-compose
docker-compose up
### Import the Postman Collection to your personal Postman
Use the provided json file in the root folder of the directory to get all the HTTP requests in one place. 
You may perform the following actions:
### Using the APIs
1. CREATE HOTEL
2. GET ALL HOTELS/GET HOTEL BY ID
3. CREATE A REVIEW FOR A HOTEL
4. SEARCH A HOTEL BASED ON CERTAIN CRITERIA TO COMPARE IT WITH OTHERS
5. DELETE HOTEL
6. CREATE RESERVATION
7. DELETE RESERVATION
8. UPDATE A RESERVATION STATUS ASYNCHRONOUSLY
