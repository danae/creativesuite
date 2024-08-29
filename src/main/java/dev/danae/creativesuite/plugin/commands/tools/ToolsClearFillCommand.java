package dev.danae.creativesuite.plugin.commands.tools;

import dev.danae.commons.commands.CommandContext;
import dev.danae.commons.commands.CommandException;
import dev.danae.commons.commands.CommandUsageException;
import dev.danae.creativesuite.model.Manager;
import dev.danae.creativesuite.plugin.commands.ManagerCommand;
import java.util.List;
import org.bukkit.GameMode;


public class ToolsClearFillCommand extends ManagerCommand
{
  // Constructor
  public ToolsClearFillCommand(Manager manager)
  {
    super(manager, "creativesuite.tools.clearfill");
  }
    
  
  // Handle the command
  @Override
  public void handle(CommandContext context) throws CommandException, CommandUsageException
  {     
    // Assert that the command sender is a player and in creative mode
    var player = context.assertSenderIsPlayer(GameMode.CREATIVE);

    // Validate the number of arguments
    if (!context.hasAtLeastArgumentsCount(1))
      throw new CommandUsageException();

    // Create a scanner for the arguments
    var scanner = context.getArgumentsScanner();
      
    // Parse the arguments
    var targetPlayer = scanner.nextPlayer(player);
    if (targetPlayer != player)
      context.assertSenderHasPermissions("creativesuite.tools.clearfill.others");

    // Clear the inventory of the player and fill it with a hotbar
    this.getManager().clearInventory(targetPlayer);
  }

  // Handle tab completion of the command
  @Override
  public List<String> handleTabCompletion(CommandContext context)
  {
    return List.of();
  }
}
