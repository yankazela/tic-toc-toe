def searchAndReplace():
    string = input('Please enter a string: ')
    if string != 'Q':
        toSearch = input('Please enter the substring you wish to find: ')
        if toSearch != 'Q':
            toReplace = input('Please enter a string to replace the given substring: ')
            if toReplace != 'Q':
                result = string.replace(toSearch, toReplace)
                print('Your new string is: ' + result)
                searchAndReplace()
            else:
                print('Program terminated by the user.')
        else:
            print('Program terminated by the user.')
    else:
        print('Program terminated by the user.')


print('This is a recursive program that allows you to search and replace a substring in a string, to stop it you must enter "Q" at any step.')
searchAndReplace()