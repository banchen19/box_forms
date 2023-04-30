# box_forms
java模仿bds的箱子菜单

配置文件说明
默认配置文件：
```
  "ENDER_PEARL": {
    "type": "from",
    "name": "家园传送点",
    "command": "",
    "command_from": "./plugins/box_menu/home.json"
  },
```
以下为默认配置文件中的说明：

    ENDER_PEARL：物品常量名称，不可重复；
    type：目标菜单类型，包括from（打开另一个菜单）、
        command（执行命令，以玩家身份执行）、:```give @p 你要执行的命令```
        tell（向玩家发送消息）、
        opfrom（仅管理员打开指定菜单）、
        opcommand（执行命令，以控制台身份执行）；
    name：别名；
    command：要执行的命令，配合command、tell、opcommand类型使用；
    command_from：指定打开的目标菜单文件路径，仅在type为from或opfrom时有效。

常量可以通过查找org.bukkit.Material（ https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html ）类中的常量进行查询。例如，ENDER_PEARL对应的常量为Material.ENDER_PEARL。

示例配置文件中的四个菜单项分别为：

    ENDER_PEARL：家园传送点，打开指定菜单文件；
    SLIME_BALL：获取粘液科技书，执行/sf guide命令；
    PLAYER_HEAD：玩家间传送，向玩家发送一段指导使用/tpa命令的文字信息；
    BEDROCK：管理员菜单，打开指定的管理员菜单文件。

注意：配置文件中的菜单项格式必须与默认配置文件的格式一致，否则可能导致解析失败。

例如：
```j
{
  "ENDER_PEARL": {
    "type": "from",
    "name": "家园传送点",
    "command": "",
    "command_from": "./plugins/box_menu/home.json"
  },
  "SLIME_BALL": {
    "type": "command",
    "name": "获取粘液科技书",
    "command": "sf guide",
    "command_from": ""
  },
  "PLAYER_HEAD": {
    "type": "tell",
    "name": "玩家间传送",
    "command": "使用 /tpa 玩家名 进行传送申请\n使用/tpyes或/tpno同意或拒绝传送",
    "command_from": ""
  },
  "BEDROCK": {
    "type": "opfrom",
    "name": "管理员菜单",
    "command": "",
    "command_from": "./plugins/box_menu/op.json"
  }
    }

```
