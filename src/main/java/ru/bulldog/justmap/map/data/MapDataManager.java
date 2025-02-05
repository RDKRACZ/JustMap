package ru.bulldog.justmap.map.data;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

import java.util.List;

public interface MapDataManager {
     // WorldKey management

     WorldKey getWorldKey();

     List<WorldKey> registeredWorlds();

     void setCurrentWorldName(String name);

     // World map management

     MapRegionProvider getMapRegionProvider();

     int getMapHeight(Layer mapLayer, int mapLevel, int posX, int posZ);

     // Callbacks

     void onServerConnect();

     void onWorldChanged(World world);

     void onWorldSpawnPosChanged(BlockPos newPos);

     void onChunkLoad(World world, WorldChunk worldChunk);

     void onConfigUpdate();

     void onSetBlockState(BlockPos pos, BlockState state, World world);

     void onTick(boolean isServer);

     void onWorldStop();
}
