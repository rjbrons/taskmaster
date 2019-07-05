# Task Master

This is an app that allows a user to track tasks that need to be done.  A user can save a task and then update its status as it gets assigned, accepted, completed, etc.



# API
/tasks - get request to this route returns a list of all the tasks in the database.  

/tasks - post request to this route with a title and description in the body as parameters will create a new task in the db.  

/tasks/{id}/state - put request with the task id will update the task state to the next state.(Available, Assigned, Accepted, Finished)


# Deployed Site
`http://taskmaster-env.txmi2mw3a9.us-east-2.elasticbeanstalk.com`