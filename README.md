# Hi!

Here's the solution to the challenge. I tried to keep it simple, the application runs the solution of the input after startup. There's a single test that does the same thing (the one I used while developing the solution)

The project does the bare minimum to solve the problem, there's a bunch of stuff that I would implement on a real project like model validation or improve the performance of the business validations.

The solution uses a memory (h2) database. It's built on java 11 with spring boot.

I used a strategy pattern to add the business layer validations, which is the core of the solution. Everything should be clear enough.

Thanks for reading, hope it's good enough!
