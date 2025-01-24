# Rough Psuedocode
Psuedocode might look something like this:

```
instructionPointer = 0
outputString = ""
while instructionPointer < program length:
    
    if program.charAt(instructionPointer) == '+':
        # Handle plus
    if program.charAt(instructionPointer) == '-':
        # Handle minus
    ... So on for every instruction type

    instructionPointer++

```

# Casting to char
How to take an ascii value and get it as a character that can be appended to a string: https://stackoverflow.com/questions/5328996/java-change-int-to-ascii