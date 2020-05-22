package ru.bulldog.justmap.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;

public class StateUtil {
	public static final BlockState AIR = Blocks.AIR.getDefaultState();
	public static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
	public static final BlockState VOID_AIR = Blocks.VOID_AIR.getDefaultState();
	
	public static boolean checkState(BlockState state, boolean liquids, boolean plants) {
		return StateUtil.isAir(state) || (!liquids && isUnderwater(state)) || (!plants && isPlant(state));
	}
	
	public static boolean isAir(BlockState state) {
		return state.isAir() || state == AIR || state == CAVE_AIR || state == VOID_AIR;
	}
	
	public static boolean isLiquid(BlockState state, boolean lava) {
		Material material = state.getMaterial();
		return material.isLiquid() && (lava || material != Material.LAVA);
	}
	
	public static boolean isWater(BlockState state) {
		FluidState fluidState = state.getFluidState();
		return fluidState.matches(FluidTags.WATER);
	}
	
	public static boolean isUnderwater(BlockState state) {
		return isLiquid(state, false) || state.getMaterial() == Material.UNDERWATER_PLANT;
	}
	
	public static boolean isPlant(BlockState state) {
		Material material = state.getMaterial();
		return material == Material.PLANT || material == Material.REPLACEABLE_PLANT ||
			   material == Material.UNUSED_PLANT || isSeaweed(state);
	}
	
	public static boolean isSeaweed(BlockState state) {
		Material material = state.getMaterial();
		return material == Material.UNDERWATER_PLANT || material == Material.REPLACEABLE_UNDERWATER_PLANT;
	}
}
