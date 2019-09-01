/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.api.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Use {@link IElectricEquipment} instead
 */
@Deprecated
public interface ITeslaEquipment extends IElectricEquipment
{
	void onStrike(ItemStack s, EquipmentSlotType eqSlot, LivingEntity p, Map<String, Object> cache, DamageSource dmg);

	@Override
	default void onStrike(ItemStack equipped, EquipmentSlotType eqSlot, LivingEntity owner, Map<String, Object> cache, @Nullable DamageSource dmg, ElectricSource desc)
	{
		if(dmg!=null)
			onStrike(equipped, eqSlot, owner, cache, dmg);
	}
}
