package net.krinsoft.privileges.commands;

import net.krinsoft.privileges.Privileges;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krinsdeath
 */
public class UserInfoCommand extends PrivilegesCommand {

    public UserInfoCommand(Privileges plugin) {
        super(plugin);
        setName("Privileges: User Info");
        setCommandUsage("/priv user info [PLAYER]");
        setArgRange(0, 1);
        addKey("priv user info");
        addKey("pu info");
        addKey("pui");
        setPermission("privileges.user.info", "Allows the user to check information about themselves or others.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        List<String> lines = new ArrayList<String>();
        CommandSender target = sender;
        if (args.size() == 1) {
            target = plugin.getServer().getPlayer(args.get(0));
            if (target == null) {
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(ChatColor.RED + "Target must exist from Console.");
                    return;
                }
                target = sender;
            }
        } else {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.RED + "Target must exist from Console.");
                return;
            }
        }
        lines.add("=== User Info: " + ChatColor.BLUE + target.getName() + ChatColor.WHITE + " ===");
        lines.add("Is " + ChatColor.AQUA + target.getName() + ChatColor.WHITE + " an op? " + ChatColor.GREEN + (target.isOp() ? "Yes." : "No."));
        lines.add(ChatColor.AQUA + target.getName() + ChatColor.WHITE + "'s group is: " + ChatColor.GREEN + plugin.getGroupManager().getGroup((Player)target).getName());
        for (String line : lines) {
            sender.sendMessage(line);
        }
    }
}