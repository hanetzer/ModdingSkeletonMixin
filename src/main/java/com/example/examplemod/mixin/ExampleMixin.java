package com.example.examplemod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.util.WeightedList;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

public class ExampleMixin {
	@Mixin(targets = "net/minecraft/entity/ai/brain/task/MultiTask$RunType$1")
	public static class RunOneMixin {
		/**
		 * @reason example
		 * @author hanetzer
		 */
		@Overwrite(remap = false)
		public void func_220630_a(WeightedList<Task<? super LivingEntity>> tasks, ServerWorld world, LivingEntity entity, long time) {
			tasks.func_220655_b().filter((task) -> {
				return task.getStatus() == Task.Status.STOPPED;
			}).filter((task) -> {
				return task.start(world, entity, time);
			}).findFirst();
		}
	}

	@Mixin(targets = "net/minecraft/entity/ai/brain/task/MultiTask$RunType$2")
	public static class TryAllMixin {
		/**
		 * @reason example
		 * @author hanetzer
		 */
		@Overwrite
		public <E extends LivingEntity> void func_220630_a(WeightedList<Task<? super E>> tasks, ServerWorld world, E entity, long time) {
			tasks.func_220655_b().filter((task) -> {
				return task.getStatus() == Task.Status.STOPPED;
			}).forEach((task) -> {
				task.start(world, entity, time);
			});
		}
	}
}
