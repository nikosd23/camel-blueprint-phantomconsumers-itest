Integration Test for KAraf 2.2.5 and Camel 2.9.0.

We used to have problems with phantom consumers which upon route restarts were not cleared from Context.
This was leading to lost messages.
We have created this integration test to check if problem was solved. 