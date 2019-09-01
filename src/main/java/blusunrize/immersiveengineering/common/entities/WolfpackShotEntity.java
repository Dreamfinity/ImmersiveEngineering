/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.entities;

import blusunrize.immersiveengineering.api.tool.BulletHandler.IBullet;
import blusunrize.immersiveengineering.common.IEConfig;
import blusunrize.immersiveengineering.common.util.IEDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class WolfpackShotEntity extends RevolvershotHomingEntity
{
	public WolfpackShotEntity(World world)
	{
		super(world);
		trackCountdown = 15;
		redirectionSpeed = .1875;
	}

	public WolfpackShotEntity(World world, double x, double y, double z, double ax, double ay, double az, IBullet type)
	{
		super(world, x, y, z, ax, ay, az, type);
		trackCountdown = 15;
		redirectionSpeed = .1875;
	}

	public WolfpackShotEntity(World world, LivingEntity living, double ax, double ay, double az, IBullet type, ItemStack stack)
	{
		super(world, living, ax, ay, az, type, stack);
		trackCountdown = 15;
		redirectionSpeed = .1875;
	}

	@Override
	public void onImpact(RayTraceResult mop)
	{
		if(!this.world.isRemote&&mop instanceof EntityRayTraceResult)
		{
			Entity hit = ((EntityRayTraceResult)mop).getEntity();
			if(hit.hurtResistantTime > 0)
				hit.hurtResistantTime = 0;
			hit.attackEntityFrom(IEDamageSources.causeWolfpackDamage(this, world.getPlayerByUuid(shootingEntity)),
					IEConfig.TOOLS.bulletDamage_WolfpackPart.get().floatValue());
		}
		this.remove();
	}
}