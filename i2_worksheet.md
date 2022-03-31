# Iteration 2 Worksheet

## **Paying off technical debt**

Show two instances of your group paying off technical debt. For these two instances:

- Explain how you are paying off the technical debt.
- Show commits, links to lines in your commit where you paid off technical debt.
- Classify the debt, and justify why you chose that classification with 1-3 sentences.

Example of how to link to a diff - click on he commit in the commit log, then click on the margin to the line: https://code.cs.umanitoba.ca/3350-winter-2021-a01/sample-project/-/commit/8e38ae9c3084d62adc4ac5fafa3b87d7d862dc26#72899361f89777662df76c5ce0ed847af04dff86_35_41

### **Response**

The two examples of technical debt are the Stub Database, and the GameUI.

**Stub Database** - As we did not have a real database, we had to implement a database from scratch, which proved to be challenging, and a lengthy endeavor. We started paying of this technical debt here

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/8399fd9d3333e0505186e208df554868df741485

noting that throughout we have to replace many instances of *Strings* with a new type *Sentence*, though an explicit example exists in GameLogic.java at line 28/29. 

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/84f3e76536ec927e170e5c46cc0032dccfc03e09

In the above commit, we have to remake some of the tests, as the real database has to be handled in a certain way, see line 23-31 for an instance of modifying a test.

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/728ebefb7e3291ba6eb14dcf5ceeb20a4b8656e1

This is the most recent commit of our database, and here there is not a whole lot of technical debt still evident, as by this point, most of the debt has been paid off, and we are working on newer things.

We would classify this as a deliberate reckless. We knew we would have to change the database, but our timeline for Iteration 1 was too tight to fit designing a proper database for our system. In this case, it was deliberate because we specifically chose to implement a stub database, and reckless because we put the stub together quickly, and beyond including an interface, did not do much else to make paying off that debt easier.

**Game UI** - We had originally built GameUI as a very large single file, that contained everything it needed to. However, this is technical debt as we are now needing to refactor code into smaller files, to allow for better maintenance and readability.

We are repaying this debt by spending time refactor the GameUI file into smaller files that have a more focused purpose.

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/d103f81e9acec76daee6bdfe610b21be7718e450

As is shown, we have split out the DragShadowTemplate, and the TimerUI into their own files, reducing the technical debt.


We would classify this debt as deliberate prudent. We needed to get the GameUI working for the first Iteration, and were not particularly concerned with the upkeep of it at the time. It was deliberate because we knew that the file was too large, and should have been split into multiple smaller pieces. It was prudent because we knew that it would not severely indebt us, as much of the debt we are now paying off is primarily refactoring.


## **SOLID**

Find a SOLID violation in the project of group with group number n-1 in the same section of the course as you (group 1 does group 16). Open an issue in their project with the violation, clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure your links in the issues are to specific commits (not to main, or develop as those will be changed).

Provide a link to the issue you created here.

### **Response**

Issue create for Group 16 violating the dependency inversion principle, linked here.

https://code.cs.umanitoba.ca/winter-2022-a02/group-16/pet-exchange-app/-/issues/25


## **Retrospective**

Describe how the retrospective has changed the way you are doing your project. Is there evidence of the change in estimating/committing/peer review/timelines/testing? Provide those links and evidence here - or explain why there is not evidence.

### **Response**

There is little evidence of retrospectives changing the way we are working as we had made all expectations clear very early on. In addition, we have been very successful with our current strategy, and so do not feel it necessary to change the way we are working.

That being said, the retrospectives have provided us the ability to change one thing, and that is how much individuals work. During iteration 1, one of our members ended up doing a large majority of the work. Due to the retrospective, we have decided that this group member should have to do less work. Owing to this, there are no issues assigned to the group member, and thus we cannot link any evidence of such.


## **Design patterns**

Show links to your project where you use a well-known design pattern. Which pattern is it? Provide links to the design pattern that you used.

Note: Though Dependency Injection is a programming pattern, we would like to see a programming pattern other than Dependency Injections.

### **Response**

We used the well known design pattern of a Singleton. Within this commit:

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/728ebefb7e3291ba6eb14dcf5ceeb20a4b8656e1

In the services.java you can see that on lines 10 and 20 we implement a singleton object. There is only one instance of each objected, created if it doesn't exist, but if it does exist, return the same one.

The link to the design pattern we used can be found here, taken from the course schedule.

https://refactoring.guru/design-patterns/singleton


## **Iteration 1 Feedback fixes**

Provide a link to an issue opened by the grader.

Explain what the issue was, and why it was flagged. Explain what you did to refactor or fix your code to address the issue. Provide links to the commits where you fixed the issue.

### **Response**

No issues were opened by the graders, however there were some code suggestions, one of which in particular was about the restructuring of GameUI.

In particular, the GameUI has a lot of *stuff* going on, and much of it could be separated into their own files, to allow for cleaner code, and easier modification.

To fix this problem, we refactored the code, as indicated by the following commit

https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/d103f81e9acec76daee6bdfe610b21be7718e450

We now have the files DragShadowTemplate and TimerUI, and have refactor the corresponding functions from GameUI into their own files.