# MineBuilder

This is a Minecraft Server Plugin for Spigot.

# Tested on:
Java 8
Spigot Version 1.16.1-R0.1+

# How-to use
Put the jar file into the plugins folder of your Spigot server and restart.

Config files will be created as soon as you add a Block.

# Commands
##### mbGetId:
    Click on something and store the block/entity. For entitys you need to do a right click.
#### Following commands can be used from the server consol.
#### Instead of /mbgetid you need to add the block/entity name before <minExp> (/mbaddplace <name> <minExp>...)
##### mbaddplace:
    use after /mbgetid - /mbaddplace <minExp> <maxExp> <amount for exp> 
##### mbaddbreak:
    use after /mbgetid - /mbaddbreak <minExp> <maxExp> <amount for exp> 
##### mbaddkill
    use after /mbgetid - mbaddkill <entityName> <minExp> <maxExp> <amount for exp> 
##### mbLoadCfg:
    reloads the config      
