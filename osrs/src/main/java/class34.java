import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ak")
public class class34 {
	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "Llg;"
	)
	@Export("SequenceDefinition_animationsArchive")
	public static AbstractArchive SequenceDefinition_animationsArchive;
	@ObfuscatedName("l")
	public static short[][] field193;
	@ObfuscatedName("y")
	@ObfuscatedGetter(
		intValue = -1698316323
	)
	int field190;
	@ObfuscatedName("d")
	@ObfuscatedGetter(
		intValue = -1677553367
	)
	int field191;

	class34(int var1, int var2) {
		this.field190 = var1;
		this.field191 = var2;
	}

	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "(Lac;I)Z",
		garbageValue = "155959181"
	)
	boolean method501(class30 var1) {
		if (var1 == null) {
			return false;
		} else {
			switch(this.field190) {
			case 1:
				return var1.vmethod3949(this.field191);
			case 2:
				return var1.vmethod3989(this.field191);
			case 3:
				return var1.vmethod3951((char)this.field191);
			case 4:
				return var1.vmethod3953(this.field191 == 1);
			default:
				return false;
			}
		}
	}

	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "(IIILgv;Lgr;I)Z",
		garbageValue = "-1815573650"
	)
	public static final boolean method502(int var0, int var1, int var2, RouteStrategy var3, CollisionMap var4) {
		int var5 = var0;
		int var6 = var1;
		byte var7 = 64;
		byte var8 = 64;
		int var9 = var0 - var7;
		int var10 = var1 - var8;
		class203.directions[var7][var8] = 99;
		class203.distances[var7][var8] = 0;
		byte var11 = 0;
		int var12 = 0;
		class203.bufferX[var11] = var0;
		int var20 = var11 + 1;
		class203.bufferY[var11] = var1;
		int[][] var13 = var4.flags;

		while (true) {
			label305:
			while (true) {
				int var14;
				int var15;
				int var16;
				int var17;
				int var18;
				int var19;
				do {
					do {
						do {
							label282:
							do {
								if (var20 == var12) {
									class203.field2291 = var5;
									class203.field2294 = var6;
									return false;
								}

								var5 = class203.bufferX[var12];
								var6 = class203.bufferY[var12];
								var12 = var12 + 1 & 4095;
								var18 = var5 - var9;
								var19 = var6 - var10;
								var14 = var5 - var4.xInset;
								var15 = var6 - var4.yInset;
								if (var3.hasArrived(var2, var5, var6, var4)) {
									class203.field2291 = var5;
									class203.field2294 = var6;
									return true;
								}

								var16 = class203.distances[var18][var19] + 1;
								if (var18 > 0 && class203.directions[var18 - 1][var19] == 0 && (var13[var14 - 1][var15] & 19136782) == 0 && (var13[var14 - 1][var15 + var2 - 1] & 19136824) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2 - 1) {
											class203.bufferX[var20] = var5 - 1;
											class203.bufferY[var20] = var6;
											var20 = var20 + 1 & 4095;
											class203.directions[var18 - 1][var19] = 2;
											class203.distances[var18 - 1][var19] = var16;
											break;
										}

										if ((var13[var14 - 1][var17 + var15] & 19136830) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var18 < 128 - var2 && class203.directions[var18 + 1][var19] == 0 && (var13[var14 + var2][var15] & 19136899) == 0 && (var13[var14 + var2][var15 + var2 - 1] & 19136992) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2 - 1) {
											class203.bufferX[var20] = var5 + 1;
											class203.bufferY[var20] = var6;
											var20 = var20 + 1 & 4095;
											class203.directions[var18 + 1][var19] = 8;
											class203.distances[var18 + 1][var19] = var16;
											break;
										}

										if ((var13[var14 + var2][var17 + var15] & 19136995) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var19 > 0 && class203.directions[var18][var19 - 1] == 0 && (var13[var14][var15 - 1] & 19136782) == 0 && (var13[var14 + var2 - 1][var15 - 1] & 19136899) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2 - 1) {
											class203.bufferX[var20] = var5;
											class203.bufferY[var20] = var6 - 1;
											var20 = var20 + 1 & 4095;
											class203.directions[var18][var19 - 1] = 1;
											class203.distances[var18][var19 - 1] = var16;
											break;
										}

										if ((var13[var17 + var14][var15 - 1] & 19136911) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var19 < 128 - var2 && class203.directions[var18][var19 + 1] == 0 && (var13[var14][var15 + var2] & 19136824) == 0 && (var13[var14 + var2 - 1][var15 + var2] & 19136992) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2 - 1) {
											class203.bufferX[var20] = var5;
											class203.bufferY[var20] = var6 + 1;
											var20 = var20 + 1 & 4095;
											class203.directions[var18][var19 + 1] = 4;
											class203.distances[var18][var19 + 1] = var16;
											break;
										}

										if ((var13[var17 + var14][var15 + var2] & 19137016) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var18 > 0 && var19 > 0 && class203.directions[var18 - 1][var19 - 1] == 0 && (var13[var14 - 1][var15 - 1] & 19136782) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2) {
											class203.bufferX[var20] = var5 - 1;
											class203.bufferY[var20] = var6 - 1;
											var20 = var20 + 1 & 4095;
											class203.directions[var18 - 1][var19 - 1] = 3;
											class203.distances[var18 - 1][var19 - 1] = var16;
											break;
										}

										if ((var13[var14 - 1][var17 + (var15 - 1)] & 19136830) != 0 || (var13[var17 + (var14 - 1)][var15 - 1] & 19136911) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var18 < 128 - var2 && var19 > 0 && class203.directions[var18 + 1][var19 - 1] == 0 && (var13[var14 + var2][var15 - 1] & 19136899) == 0) {
									var17 = 1;

									while (true) {
										if (var17 >= var2) {
											class203.bufferX[var20] = var5 + 1;
											class203.bufferY[var20] = var6 - 1;
											var20 = var20 + 1 & 4095;
											class203.directions[var18 + 1][var19 - 1] = 9;
											class203.distances[var18 + 1][var19 - 1] = var16;
											break;
										}

										if ((var13[var14 + var2][var17 + (var15 - 1)] & 19136995) != 0 || (var13[var14 + var17][var15 - 1] & 19136911) != 0) {
											break;
										}

										++var17;
									}
								}

								if (var18 > 0 && var19 < 128 - var2 && class203.directions[var18 - 1][var19 + 1] == 0 && (var13[var14 - 1][var15 + var2] & 19136824) == 0) {
									for (var17 = 1; var17 < var2; ++var17) {
										if ((var13[var14 - 1][var17 + var15] & 19136830) != 0 || (var13[var17 + (var14 - 1)][var15 + var2] & 19137016) != 0) {
											continue label282;
										}
									}

									class203.bufferX[var20] = var5 - 1;
									class203.bufferY[var20] = var6 + 1;
									var20 = var20 + 1 & 4095;
									class203.directions[var18 - 1][var19 + 1] = 6;
									class203.distances[var18 - 1][var19 + 1] = var16;
								}
							} while(var18 >= 128 - var2);
						} while(var19 >= 128 - var2);
					} while(class203.directions[var18 + 1][var19 + 1] != 0);
				} while((var13[var14 + var2][var15 + var2] & 19136992) != 0);

				for (var17 = 1; var17 < var2; ++var17) {
					if ((var13[var17 + var14][var15 + var2] & 19137016) != 0 || (var13[var14 + var2][var17 + var15] & 19136995) != 0) {
						continue label305;
					}
				}

				class203.bufferX[var20] = var5 + 1;
				class203.bufferY[var20] = var6 + 1;
				var20 = var20 + 1 & 4095;
				class203.directions[var18 + 1][var19 + 1] = 12;
				class203.distances[var18 + 1][var19 + 1] = var16;
			}
		}
	}

	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-143409830"
	)
	public static void method503() {
		ItemComposition.ItemDefinition_cachedSprites.clear();
	}
}
