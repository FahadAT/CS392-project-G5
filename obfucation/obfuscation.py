import os
import re
import time


num_of_files = 0
num_of_obfuscated_filename = 0
num_of_nonobfuscated_filename = 0

list_of_non_obfuscated = []
list_of_obfuscated = []
list_char = []

def get_all_files(directory_path):
    list_files = []
    for root, dirs, files in os.walk(directory_path):
        for file in files:
            if file.endswith(".java"):
                list_files.append(os.path.join(root, file))

    return list_files




def check_filename_rules(line):
    rules_list = open('rulepack-filename').readlines()
    for rule in rules_list:
        rule = rule.strip()
        if "char:" in rule:
            rule = rule.split(":")[1] + "."
            
            if rule in line:
                return True

        if "regex:" in rule:
            regex_rule = rule.split(":")[1] + ".java"
            p = re.compile(regex_rule)
            if p.findall(line):
                return True
            if p.match(line):
                return True

    return False

def binary_search(arr, low, high, x):
 
    # Check base case
    if high >= low:
 
        mid = (high + low) // 2
 
        # If element is present at the middle itself
        if arr[mid] == x:
            return mid
 
        # If element is smaller than mid, then it can only
        # be present in left subarray
        elif arr[mid] > x:
            return binary_search(arr, low, mid - 1, x)
 
        # Else the element can only be present in right subarray
        else:
            return binary_search(arr, mid + 1, high, x)
 
    else:
        # Element is not present in the array
        return -1

def check_file(file_path):
   
    try:
        file = open('%s' % file_path).read()

        file = open('%s' % file_path)

       
        global num_of_obfuscated_filename
        global num_of_nonobfuscated_filename
        global list_of_obfuscated
        global list_of_non_obfuscated

        name_of_file = file_path.split('\\')[-1]


        if name_of_file != 'R.java' and (check_filename_rules(name_of_file) or binary_search(list_char,0,len(list_char)-1, name_of_file) != -1):

            num_of_obfuscated_filename += 1
            list_of_obfuscated.append(file_path+"\n")
        
        
        else:
            num_of_nonobfuscated_filename += 1
            list_of_non_obfuscated.append(name_of_file +"\n")

               



      

    except UnicodeDecodeError as e:
        print(e)

    except IOError as e:
        print(e)





    
def run_checker(code_dir):
    start = time.time()
    global list_char
    file = open("z-a.txt",'r')

    
    Lines = file.readlines()
 

    for line in Lines:
        list_char.append(line.strip())
        
    file.close()

    list_char.sort()
    
   

    
    list_java_files = get_all_files(code_dir)
    num_of_files = len(list_java_files)
    print("%s %d%s" % ("number of files:",num_of_files," File(s)"))
    
    for filepath in list_java_files:
        check_file(filepath)
    print("%s %d%s  " % ("total of file(s)",num_of_files," file(s)"))
    print("%s %d%s\n%s %d%s" % ("Number of obfuscated file name: ",num_of_obfuscated_filename," file(s)","Number of not obfuscated file name: ",num_of_nonobfuscated_filename," file(s)"))
    print("%s %0.2f%s  " % ("Percentage of obfuscated file name:",(num_of_obfuscated_filename/num_of_files)*100,"%"))
    print("%s %0.2f%s  " % ("Percentage of non obfuscated file name:",(num_of_nonobfuscated_filename/num_of_files)*100,"%"))



    end = time.time()

    print(f"time is {end - start}")
    
   
  
    
    
run_checker("Source Code")

   