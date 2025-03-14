# Ixo User Guide


                                         Hello I'm Ixo!
                                        ooooooooooooooooo
                                          o           o
                                            o       o
                                              o   o
                                                o
                                              o   o
                                            o       o
                                          o           o
                                        ooooooooooooooooo
              I am a chatbot dedicated to help you create various types of tasks and
                                       keep track of them!


# Task List Program

## Available Commands

### List all tasks

Shows all currently saved tasks, the type of task and whether or they are marked 

Example: `list`

Example output:

`list`
```
Here are the current list of tasks:
[T] [ ] Read a book
[D] [X] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```

### Add ToDo

Add a ToDo task

Example: `todo <description>`

Example output:

`todo Read a book`
```
Okay, I have added this todo:
    [T] [ ] Read a book
Now you have 1 task in the list.
```

### Add Deadline

Add a Deadline task

usage: `deadline <description> /by <date>`

Example output:

`deadline Complete CS2113 iP /by 14 March`
```
Okay, I have added this deadline:
    [D] [ ] Complete CS2113 iP (by: 14 March)
Now you have 2 tasks in the list.
```

### Add Event

Add a Event task

Example: `event <description> /from <date> /to <date>`

Example output:

`event EE2211 Midterms /from 15 March /to 15 March`
```
Okay, I have added this event:
    [E][ ] EE2211 Midterms (from: 15 March to: 15 March)
Now you have 3 tasks in the list.
```

### Mark a task as done

Mark a task as done, a marked task cannot be remarked

Example: `mark <task number>`

`list`
```
Here are the current list of tasks:
[T] [ ] Read a book
[D] [ ] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`mark 2`
```
Well done! The task Complete CS2113 iP has been marked as done.
```

`list`
```
Here are the current list of tasks:
[T] [ ] Read a book
[D] [X] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`mark 2`
```
This task had already been marked
```

### Unmark a task

Remove a task's done status. An undone task cannot be unmarked

Example: `unmark <task number>` 

`list`
```
Here are the current list of tasks:
[T] [ ] Read a book
[D] [X] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`unmark 2`
```
The task Complete CS2113 iP has been unmarked.
```

`list`
```
Here are the current list of tasks:
[T] [ ] Read a book
[D] [ ] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`unmark 2`
```
This task had already been marked
```

### Delete Tasks

Remove tasks from the list

Example: `delete <task number>`

Example output:

`delete 1`
```
Task deleted: [T] [ ] Read a book
```
`list`
```
Here are the current list of tasks:
[D] [ ] Complete CS2113 iP (by: 14 March)
[E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`delete 3`
```
That is not a valid number
```

### Find tasks

Locate specific tasks by search key, also shows the index in the list

Example: `find <search key>`

`find e`
```
These are the tasks matching your searchkey
1: [D] [ ] Complete CS2113 iP (by: 14 March)
2: [E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`find M`
```
These are the tasks matching your searchkey
2: [E] [ ] EE2211 Midterm (from: 15 March to: 15 March)
```
`find a`
```
There are no tasks matching your search key
```

### Exit program

Exits the task list program and saves all contents of the task list

usage: `bye`

`bye`

```
Starting Save...
```