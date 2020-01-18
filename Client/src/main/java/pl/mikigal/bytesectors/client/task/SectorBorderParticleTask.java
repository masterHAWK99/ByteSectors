package pl.mikigal.bytesectors.client.task;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.mikigal.bytesectors.client.Configuration;
import pl.mikigal.bytesectors.client.utils.BlockUtils;
import pl.mikigal.bytesectors.commons.data.Sector;
import pl.mikigal.bytesectors.commons.data.SectorManager;

public class SectorBorderParticleTask implements Runnable {

    @Override
    public void run() {
        Sector sector = SectorManager.getSector(Configuration.getSectorId());
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location location = player.getLocation();
            if (sector.getDistanceToBorder(location.getBlockX(), location.getBlockZ()) > 150) {
                continue;
            }

            for (Location loc : BlockUtils.sphere(location, 8, 0, false, true, 1)) {
                if ((loc.getBlockX() == sector.getMinimum().getX() || loc.getBlockX() == sector.getMaximum().getX() ||
                        loc.getBlockZ() == sector.getMinimum().getZ() || loc.getBlockZ() == sector.getMaximum().getZ()) &&
                        (loc.getBlock() == null || loc.getBlock().getType() == Material.AIR)) {

                    player.playEffect(loc, Effect.PORTAL, 0);
                }
            }
        }
    }
}