# Commands

1. Start application
   * `.\gradlew bootRun`
2. Add operators
   * `curl --location 'http://localhost:8080/api/operators/add' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=John Doe'`
   * `curl --location 'http://localhost:8080/api/operators/add' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=De Maria'`
   * `curl --location 'http://localhost:8080/api/operators/add' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=Wayne'`
3. Get all operators
   * `curl --location 'http://localhost:8080/api/operators/all'`
4. Book appointment
   * `curl --location 'http://localhost:8080/api/appointments/book' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --data-urlencode 'operatorId=6610251791b28d1fadb7c1e4' \
      --data-urlencode 'date=2024-04-15' \
      --data-urlencode 'startTime=9' \
      --data-urlencode 'endTime=10' \
      --data-urlencode 'userId=User123'`
   * `curl --location 'http://localhost:8080/api/appointments/book' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --data-urlencode 'operatorId=6610260691b28d1fadb7c1e5' \
      --data-urlencode 'date=2024-04-15' \
      --data-urlencode 'startTime=9' \
      --data-urlencode 'endTime=10' \
      --data-urlencode 'userId=User123'`
5. Reschedule
   * `curl --location --request PUT 'http://localhost:8080/api/appointments/reschedule/661031536edc04297f8570c3' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --data-urlencode 'newStartTime=4' \
      --data-urlencode 'newEndTime=5'`
6. Cancel appointment
   * `curl --location --request DELETE 'http://localhost:8080/api/appointments/cancel/661031536edc04297f8570c3a'`
7. Open Slots for any appointment
   * `curl --location 'http://localhost:8080/api/appointments/openSlots/6610251791b28d1fadb7c1e4?date=2024-04-15&dayStart=4&dayEnd=17'`
8. Get Operator Appointments
   * `curl --location 'http://localhost:8080/api/appointments/operator/6610251791b28d1fadb7c1e4?date=2024-04-15'`
