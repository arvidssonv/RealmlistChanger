# RealmlistChanger
Java program with a simple gui for chosing a realmlist before launching 1.12 World of Warcraft
<p align="center">
  <img src="https://github.com/arvidssonv/RealmlistChanger/blob/master/img/realmlistchanger.png">
</p>

### Setup
  - The program reads from the existing realmlist file in your 1.12 WoW folder, which should be setup like this:
    
    ![image](https://github.com/arvidssonv/RealmlistChanger/blob/master/img/example_realmlist.png)
    
    <sup>You can add more realmlists by adding each on a new line</sup>
        

  - Edit the wowPath variable (line 34) to match your 1.12 WoW directory. For example:
    ```
    String wowPath = "C:\\Program Files (x86)\\World of Warcraft\\"
    ```
  
  - Compile and run!
    ```
    $ javac RealmlistChanger.java
    $ java RealmlistChanger
    ```

