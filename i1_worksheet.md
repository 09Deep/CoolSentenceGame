Iteration 1 Worksheet
=====================

Adding a feature
-----------------

Tell the story of how one of your features was added to the project.
Provide links to the
feature, user stories, and merge requests (if used), associated tests, and merge commit
that was used complete the feature.

Use one or two paragraphs which can have point-form within them.

### Response

We will be showcasing the Main Menu feature. 

This feature was first branched out at https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/36eeed5682447eed435e5c16ad8a47ba7351e21a.<br>
The first commit, and the one with the majority of the work, was done at https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/60b42bbf91b07a3a96c389ff46759d1a4a6e06e4.<br>
This included the bulk of work done, the original design, the set of starting buttons, and what would eventually get linked to starting a game.<br>
The next step was testing, which was implementation at the https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/4153082c83b4dd1adb9dbe0905bc29b536794b78 commit. <br>
This features the testing needed to ensure that the UI would run smoothly.<br>
After doing that testing, we found that the back button was not correctly implemented, and had to change that in https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/c5c06d6fa9ec9e2ba09bc3c3f83065be46491461<br>
Finally, the code was rebased off of dev, then merged back into Dev via a merge request. Those are list below in the order: Rebase Merge, Merge Commit, Merge Request.<br>
https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/43719f42c6d3996606ba12365f1d2794c47ef1a5<br>
https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/commit/522dc86250368389f7773c4b825d5faffc856ec6<br>
https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/merge_requests/1<br>

That was the journey of adding the main menu.

Exceptional code
----------------

Provide a link to a test of exceptional code. In a few sentences,
provide an explanation of why the exception is handled or thrown
in the code you are testing.

### Response

Our code does not currently have exceptions being thrown. The reason for this is that most of the code that is currently developed does not factor in user input, so trying to catch / warn of issues in those cases are low. Additionally, much of the code is unable to throw exceptions to begin with. In short, much of the code we have written is deterministic, and unlikely to thrown exceptions. This negates much of the practical concern for adding exception handling. In future iterations this exceptions will be handled, as the code begins to require it.

Branching
----------

Provide a link to where you describe your branching strategy.

Provide screen shot of a feature being added using your branching strategy
successfully. The [GitLab Graph tool can do this](https://code.cs.umanitoba.ca/comp3350-summer2019/cook-eBook/-/network/develop),
as well as using `git log --graph`.

### Response

Our branching strategy is the Gitflow Style. A link to the page is provided within our readme at https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/blob/06ff41144f94550694539a804ceecac24b8e7608/README.md<br>
Provided at https://code.cs.umanitoba.ca/winter-2022-a02/group-1/cool-sentence-game/-/blob/a7bf18a7832fae8e05afb11073c57383335e3ca7/worksheet_I1_feature_graph.png is a screenshot of the GitLab Graph tool.<br>
As it shows, the feature was branched off of our Dev branch, worked on, with additional rebases as other features were added, before finally being merged back into dev, successfully integrating the feature.

SOLID
-----

Find a SOLID violation in the project of group `(n%16)+1` (group 16 does group 1).
Open an issue in their project with the violation,
clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure
your links in the issues are to **specific commits** (not to `main`, or `develop` as those will be changed).

Provide a link to the issue you created here.

### Response

We could not find a SOLID violation in our assigned group. 
There was not very much code to go over and thus not many opportunities to find violations.

Agile Planning
--------------

Write a paragraph about any plans that were changed. Did you
'push' any features to iteration 2? Did you change the description
of any Features or User Stories? Have links to any changed or pushed Features
or User Stories.

### Response
 
We decided to include create a round, complete a round, and main menu as features for the first iteration. 
And there are 10 user stories related to thses 4 features for iteration one.During our iteration one planning,
tasks were divided among us based on the user stories. Everyone completed the tasks related to their user 
story by the end of the iteration, so no user story or feature is pushed to iteration 2. 
