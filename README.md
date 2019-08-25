# RealmlistChanger

Java program with a simple GUI for choosing a realm before launching 1.12 World of Warcraft

![image](https://github.com/arvidssonv/RealmlistChanger/blob/master/img/realmlistchanger.png)

### Setup

 The program reads from the existing realmlist.wtf file in your 1.12 WoW folder, which should be setup like this:
```
set realmlist login.nostalrius.org
set realmlist login.kronos-wow.com
set realmlist logon.lightshope.org
set realmlist login.retro-wow.com
set realmlist pvp.henhou.se
```
With each servers realmlist on a new line.

The program will edit the realmlist.wtf file using the # character to "comment out" the realms that are not chosen, causing them to be ignored when the game starts.
```
set realmlist login.nostalrius.org
#set realmlist login.kronos-wow.com
#set realmlist logon.lightshope.org
#set realmlist login.retro-wow.com
#set realmlist pvp.henhou.se
```

Edit the wowPath variable to match your 1.12 WoW directory. For example:
```
String wowPath = "C:\\Program Files (x86)\\World of Warcraft\\"
```
Compile and run!
```
$ javac RealmlistChanger.java
$ java RealmlistChanger
```