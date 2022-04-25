# Retrospective

## Problems with the Database

Throughout the course of this project, the database has been a sore spot. During iteration 2 especially, the transition from a mock database to a fully working database proved to be incredibly difficult. This difficulty was caused by the database that we had wanted to use, during the iteration 2, we attempted to use the Room database. However, the implementation of this was much more difficult than we had expected and we had to fall back to using an HSQLDB. By this time we only had a week left in the iteration, and because of that, we could not complete the features associated with the database in time for iteration 2.

The database can be improved in iteration 3 by successfully implementing the interface we used for iteration 1 on top of a real HSQLDB. This can be improved in the following ways: Successfully implement the interface. Add additional tables to the database that have become needed as the project becomes more complicated. Create integration tests that will work on a real database. The full database should have additional functionality that relates to the extra data that is going to be stored within them. User preferences should no longer be stored in memory, but in database, and only read on load or change.

This will be considered successful if all of the following condition are met: integration tests involving the database run successfully, the database can be swapped for the mock database and all related tests still run successfully, and if all user stored data persists between sessions, include theme and game speed. Additionally, the classes Account, AccountData, and Preferences should all be removed, and their functionality, if any, delegated to the interface of the database.

## Velocity Chart

![Velocity chart](i1_i2_vc.png)

Above is the velocity chart for our first 2 iterations. In both iterations, we slightly overestimated how long the tasks were going to take. Noticeably, the amount of work both estimated and achieved in iteration 2 was vastly higher than that of iteration 1. We believe that this is because none of us had used Android Studio before. Because none of us had used Android studio, it took a lot of time for us to learn how to use the tool. For example, learning how to set up emulations. Add on to this that none of us knew each other, we had some wasted time just starting to mesh as a group, and learn how to operate around each other.

The large increase in estimation in the second iteration shows how we had gotten more comfortable working with each other, and with Android Studio. We had all settled into our roles and were ready to work as team.