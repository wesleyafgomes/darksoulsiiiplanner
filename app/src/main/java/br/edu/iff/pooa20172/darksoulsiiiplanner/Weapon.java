package br.edu.iff.pooa20172.darksoulsiiiplanner;

public class Weapon extends Equipment {
    private boolean blessed, hollow, catalyst, infusable;
    private int strengthRequirement, dexterityRequirement, faithRequirement, intelligenceRequirement,
            stability, infusion, criticalMod, bleedBuildup, poisonBuildup, frostBuildup;
    private double physicalBaseAttack, magicBaseAttack, fireBaseAttack, lightningBaseAttack, darkBaseAttack,
            physicalBlock, magicBlock, fireBlock, lightningBlock, darkBlock;
    private double strengthScaling, dexterityScaling, intelligenceScaling, faithScaling, luckScaling;
    private int physicalSaturationIndex, magicSaturationIndex, fireSaturationIndex, lightningSaturationIndex, darkSaturationIndex;

    public static final int REGULAR=0,REFINED=1, RAW=2, FIRE=3, HEAVY=4, SHARP=5, POISON=6, CRYSTAL=7, BLESSED=8, DEEP=9, DARK=10, BLOOD=11, HOLLOW=12, LIGHTNING=13, SIMPLE=14, CHAOS=15;

    private static double[][] saturationCurves = {
            // Index 0
            {0, 0, 0.8344518907, 1.917067028, 3.118507614, 4.404263484, 5.756590123, 7.164449132, 8.620231934, 10.11834044, 11.65446426, 13.22517121, 14.8276568, 16.45958186, 18.11896248, 19.80409249, 21.51348727, 23.24584203, 25, 27.71472262, 30.40370474, 33.06592105, 35.70024948, 38.30545618, 40.88017724, 43.42289595, 45.93191442, 48.40531749, 50.84092608, 53.23623592, 55.58833502, 57.89379002, 60.14848454, 62.34738086, 64.4841523, 66.55058206, 68.53550242, 70.42271143, 72.186164, 73.77520674, 75, 75.5, 76, 76.5, 77, 77.5, 78, 78.5, 79, 79.5, 80, 80.5, 81, 81.5, 82, 82.5, 83, 83.5, 84, 84.5, 85, 85.06158775, 85.17419647, 85.32001934, 85.49270201, 85.68857199, 85.90515139, 86.14062112, 86.39357173, 86.66286929, 86.94757571, 87.2468981, 87.56015475, 87.88675135, 88.22616371, 88.57792504, 88.94161609, 89.31685768, 89.7033046, 90.10064091, 90.50857595, 90.92684119, 91.35518752, 91.79338304, 92.24121114, 92.69846893, 93.16496581, 93.64052229, 94.12496895, 94.61814556, 95.11990022, 95.63008872, 96.14857387, 96.67522499, 97.20991735, 97.7525318, 98.30295432, 98.86107568, 99.42679111, 100},
            // Index 1
            {0, 0, 1.357558438, 3.118850289, 5.073457646, 7.165236393, 9.365318221, 11.6557449, 14.02413816, 16.46139052, 18.96048948, 21.51585127, 24.12291328, 26.77786999, 29.47749377, 32.21900886, 35, 36.9915723, 38.96589678, 40.92234868, 42.86024987, 44.77886141, 46.67737466, 48.55490041, 50.41045569, 52.24294746, 54.05115207, 55.8336893, 57.58898873, 59.31524557, 61.01036121, 62.67186128, 64.29677918, 65.88148432, 67.42141717, 68.91065603, 70.34115254, 71.70123022, 72.97211886, 74.11731347, 75, 75.5, 76, 76.5, 77, 77.5, 78, 78.5, 79, 79.5, 80, 80.5, 81, 81.5, 82, 82.5, 83, 83.5, 84, 84.5, 85, 85.06158775, 85.17419647, 85.32001934, 85.49270201, 85.68857199, 85.90515139, 86.14062112, 86.39357173, 86.66286929, 86.94757571, 87.2468981, 87.56015475, 87.88675135, 88.22616371, 88.57792504, 88.94161609, 89.31685768, 89.7033046, 90.10064091, 90.50857595, 90.92684119, 91.35518752, 91.79338304, 92.24121114, 92.69846893, 93.16496581, 93.64052229, 94.12496895, 94.61814556, 95.11990022, 95.63008872, 96.14857387, 96.67522499, 97.20991735, 97.7525318, 98.30295432, 98.86107568, 99.42679111, 100},
            // Index 2
            {0, 0, 1.163621518, 2.673300248, 4.348677982, 6.141631194, 8.027415618, 9.990638489, 12.02068985, 14.1097633, 16.25184813, 18.44215823, 20.67678282, 22.95245999, 25.26642323, 27.61629331, 30, 32.24051883, 34.46163388, 36.66264226, 38.8427811, 41.00121909, 43.13704649, 45.24926296, 47.33676266, 49.39831589, 51.43254608, 53.43790047, 55.41261233, 57.35465127, 59.26165636, 61.13084394, 62.95887657, 64.74166986, 66.47409431, 68.14948804, 69.75879661, 71.288884, 72.71863372, 74.00697765, 75, 75.5, 76, 76.5, 77, 77.5, 78, 78.5, 79, 79.5, 80, 80.5, 81, 81.5, 82, 82.5, 83, 83.5, 84, 84.5, 85, 85.06158775, 85.17419647, 85.32001934, 85.49270201, 85.68857199, 85.90515139, 86.14062112, 86.39357173, 86.66286929, 86.94757571, 87.2468981, 87.56015475, 87.88675135, 88.22616371, 88.57792504, 88.94161609, 89.31685768, 89.7033046, 90.10064091, 90.50857595, 90.92684119, 91.35518752, 91.79338304, 92.24121114, 92.69846893, 93.16496581, 93.64052229, 94.12496895, 94.61814556, 95.11990022, 95.63008872, 96.14857387, 96.67522499, 97.20991735, 97.7525318, 98.30295432, 98.86107568, 99.42679111, 100},
            // Empty Indexes 3 and 4
            {}, {},
            // Index 5
            {0, 0, 0.9655172414, 1.931034483, 2.896551724, 3.862068966, 4.827586207, 5.793103448, 6.75862069, 7.724137931, 8.689655172, 9.655172414, 10.62068966, 11.5862069, 12.55172414, 13.51724138, 14.48275862, 15.44827586, 16.4137931, 17.37931034, 18.34482759, 19.31034483, 20.27586207, 21.24137931, 22.20689655, 23.17241379, 24.13793103, 25.10344828, 26.06896552, 27.03448276, 28, 31.2, 34.4, 37.6, 40.8, 44, 47.2, 50.4, 53.6, 56.8, 60, 61.5, 63, 64.5, 66, 67.5, 69, 70.5, 72, 73.5, 75, 76.5, 78, 79.5, 81, 82.5, 84, 85.5, 87, 88.5, 90, 90.25641026, 90.51282051, 90.76923077, 91.02564103, 91.28205128, 91.53846154, 91.79487179, 92.05128205, 92.30769231, 92.56410256, 92.82051282, 93.07692308, 93.33333333, 93.58974359, 93.84615385, 94.1025641, 94.35897436, 94.61538462, 94.87179487, 95.12820513, 95.38461538, 95.64102564, 95.8974359, 96.15384615, 96.41025641, 96.66666667, 96.92307692, 97.17948718, 97.43589744, 97.69230769, 97.94871795, 98.20512821, 98.46153846, 98.71794872, 98.97435897, 99.23076923, 99.48717949, 99.74358974, 100},
            // Index 6
            {0, 0, 0.4166666667, 0.8333333333, 1.25, 1.666666667, 2.083333333, 2.5, 2.916666667, 3.333333333, 3.75, 4.166666667, 4.583333333, 5, 5.416666667, 5.833333333, 6.25, 6.666666667, 7.083333333, 7.5, 7.916666667, 8.333333333, 8.75, 9.166666667, 9.583333333, 10, 12.25, 14.5, 16.75, 19, 21.25, 23.5, 25.75, 28, 30.25, 32.5, 34.75, 37, 39.25, 41.5, 43.75, 46, 48.25, 50.5, 52.75, 55, 57.33333333, 59.66666667, 62, 64.33333333, 66.66666667, 69, 71.33333333, 73.66666667, 76, 78.33333333, 80.66666667, 83, 85.33333333, 87.66666667, 90, 90.25641026, 90.51282051, 90.76923077, 91.02564103, 91.28205128, 91.53846154, 91.79487179, 92.05128205, 92.30769231, 92.56410256, 92.82051282, 93.07692308, 93.33333333, 93.58974359, 93.84615385, 94.1025641, 94.35897436, 94.61538462, 94.87179487, 95.12820513, 95.38461538, 95.64102564, 95.8974359, 96.15384615, 96.41025641, 96.66666667, 96.92307692, 97.17948718, 97.43589744, 97.69230769, 97.94871795, 98.20512821, 98.46153846, 98.71794872, 98.97435897, 99.23076923, 99.48717949, 99.74358974, 100},
            // Empty Index 7
            {},
            // Index 8
            {0, 0, 0.9696845986, 2.227750207, 3.623898318, 5.118025995, 6.689513015, 8.325532074, 10.01724154, 11.75813608, 13.54320677, 15.36846519, 17.23065235, 19.12705, 21.05535269, 23.01357776, 25, 27.24051883, 29.46163388, 31.66264226, 33.8427811, 36.00121909, 38.13704649, 40.24926296, 42.33676266, 44.39831589, 46.43254608, 48.43790047, 50.41261233, 52.35465127, 54.26165636, 56.13084394, 57.95887657, 59.74166986, 61.47409431, 63.14948804, 64.75879661, 66.288884, 67.71863372, 69.00697765, 70, 70.75, 71.5, 72.25, 73, 73.75, 74.5, 75.25, 76, 76.75, 77.5, 78.25, 79, 79.75, 80.5, 81.25, 82, 82.75, 83.5, 84.25, 85, 85.06158775, 85.17419647, 85.32001934, 85.49270201, 85.68857199, 85.90515139, 86.14062112, 86.39357173, 86.66286929, 86.94757571, 87.2468981, 87.56015475, 87.88675135, 88.22616371, 88.57792504, 88.94161609, 89.31685768, 89.7033046, 90.10064091, 90.50857595, 90.92684119, 91.35518752, 91.79338304, 92.24121114, 92.69846893, 93.16496581, 93.64052229, 94.12496895, 94.61814556, 95.11990022, 95.63008872, 96.14857387, 96.67522499, 97.20991735, 97.7525318, 98.30295432, 98.86107568, 99.42679111, 100},
            // Index 9
            {0, 25, 26.4319867, 28.28984153, 30.35161041, 32.55807111, 34.87877262, 37.29477215, 39.79301278, 42.36388771, 45, 46.1959639, 47.38370669, 48.56299422, 49.73357677, 50.8951874, 52.04754001, 53.19032709, 54.32321718, 55.44585178, 56.55784177, 57.65876314, 58.74815187, 59.82549774, 60.8902367, 61.94174155, 62.97931015, 64.00215051, 65.00936151, 65.99990754, 66.97258438, 67.92597223, 68.85836881, 69.76769084, 70.65132202, 71.50586441, 72.32669975, 73.10712797, 73.83637848, 74.49350432, 75, 75.5, 76, 76.5, 77, 77.5, 78, 78.5, 79, 79.5, 80, 80.5, 81, 81.5, 82, 82.5, 83, 83.5, 84, 84.5, 85, 85.06158775, 85.17419647, 85.32001934, 85.49270201, 85.68857199, 85.90515139, 86.14062112, 86.39357173, 86.66286929, 86.94757571, 87.2468981, 87.56015475, 87.88675135, 88.22616371, 88.57792504, 88.94161609, 89.31685768, 89.7033046, 90.10064091, 90.50857595, 90.92684119, 91.35518752, 91.79338304, 92.24121114, 92.69846893, 93.16496581, 93.64052229, 94.12496895, 94.61814556, 95.11990022, 95.63008872, 96.14857387, 96.67522499, 97.20991735, 97.7525318, 98.30295432, 98.86107568, 99.42679111, 100},
            // Empty Indexes 10 and 11
            {}, {},
            // Index 12
            {0, 0, 0.7142857143, 1.428571429, 2.142857143, 2.857142857, 3.571428571, 4.285714286, 5, 5.714285714, 6.428571429, 7.142857143, 7.857142857, 8.571428571, 9.285714286, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 40, 43, 46, 49, 52, 55, 56.33333333, 57.66666667, 59, 60.33333333, 61.66666667, 63, 64.33333333, 65.66666667, 67, 68.33333333, 69.66666667, 71, 72.33333333, 73.66666667, 75, 75.46296296, 75.92592593, 76.38888889, 76.85185185, 77.31481481, 77.77777778, 78.24074074, 78.7037037, 79.16666667, 79.62962963, 80.09259259, 80.55555556, 81.01851852, 81.48148148, 81.94444444, 82.40740741, 82.87037037, 83.33333333, 83.7962963, 84.25925926, 84.72222222, 85.18518519, 85.64814815, 86.11111111, 86.57407407, 87.03703704, 87.5, 87.96296296, 88.42592593, 88.88888889, 89.35185185, 89.81481481, 90.27777778, 90.74074074, 91.2037037, 91.66666667, 92.12962963, 92.59259259, 93.05555556, 93.51851852, 93.98148148, 94.44444444, 94.90740741, 95.37037037, 95.83333333, 96.2962963, 96.75925926, 97.22222222, 97.68518519, 98.14814815, 98.61111111, 99.07407407, 99.53703704, 100},
            // Empty Index 13
            {},
            // Index 14
            {0, 0, 0.7142857143, 1.428571429, 2.142857143, 2.857142857, 3.571428571, 4.285714286, 5, 5.714285714, 6.428571429, 7.142857143, 7.857142857, 8.571428571, 9.285714286, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 40, 43, 46, 49, 52, 55, 56.33333333, 57.66666667, 59, 60.33333333, 61.66666667, 63, 64.33333333, 65.66666667, 67, 68.33333333, 69.66666667, 71, 72.33333333, 73.66666667, 75, 75.46296296, 75.92592593, 76.38888889, 76.85185185, 77.31481481, 77.77777778, 78.24074074, 78.7037037, 79.16666667, 79.62962963, 80.09259259, 80.55555556, 81.01851852, 81.48148148, 81.94444444, 82.40740741, 82.87037037, 83.33333333, 83.7962963, 84.25925926, 84.72222222, 85.18518519, 85.64814815, 86.11111111, 86.57407407, 87.03703704, 87.5, 87.96296296, 88.42592593, 88.88888889, 89.35185185, 89.81481481, 90.27777778, 90.74074074, 91.2037037, 91.66666667, 92.12962963, 92.59259259, 93.05555556, 93.51851852, 93.98148148, 94.44444444, 94.90740741, 95.37037037, 95.83333333, 96.2962963, 96.75925926, 97.22222222, 97.68518519, 98.14814815, 98.61111111, 99.07407407, 99.53703704, 100},
            // Index 15
            {0, 0, 1.785714286, 3.571428571, 5.357142857, 7.142857143, 8.928571429, 10.71428571, 12.5, 14.28571429, 16.07142857, 17.85714286, 19.64285714, 21.42857143, 23.21428571, 25, 28.33333333, 31.66666667, 35, 38.33333333, 41.66666667, 45, 48.33333333, 51.66666667, 55, 58.33333333, 61.66666667, 65, 68.33333333, 71.66666667, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 90.18518519, 90.37037037, 90.55555556, 90.74074074, 90.92592593, 91.11111111, 91.2962963, 91.48148148, 91.66666667, 91.85185185, 92.03703704, 92.22222222, 92.40740741, 92.59259259, 92.77777778, 92.96296296, 93.14814815, 93.33333333, 93.51851852, 93.7037037, 93.88888889, 94.07407407, 94.25925926, 94.44444444, 94.62962963, 94.81481481, 95, 95.18518519, 95.37037037, 95.55555556, 95.74074074, 95.92592593, 96.11111111, 96.2962963, 96.48148148, 96.66666667, 96.85185185, 97.03703704, 97.22222222, 97.40740741, 97.59259259, 97.77777778, 97.96296296, 98.14814815, 98.33333333, 98.51851852, 98.7037037, 98.88888889, 99.07407407, 99.25925926, 99.44444444, 99.62962963, 99.81481481, 100},
            // Index 16
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2.407407407, 4.814814815, 7.222222222, 9.62962963, 12.03703704, 14.44444444, 16.85185185, 19.25925926, 21.66666667, 24.07407407, 26.48148148, 28.88888889, 31.2962963, 33.7037037, 36.11111111, 38.51851852, 40.92592593, 43.33333333, 45.74074074, 48.14814815, 50.55555556, 52.96296296, 55.37037037, 57.77777778, 60.18518519, 62.59259259, 65, 66.66666667, 68.33333333, 70, 71.66666667, 73.33333333, 75, 76.66666667, 78.33333333, 80, 81.66666667, 83.33333333, 85, 86.66666667, 88.33333333, 90, 90.25641026, 90.51282051, 90.76923077, 91.02564103, 91.28205128, 91.53846154, 91.79487179, 92.05128205, 92.30769231, 92.56410256, 92.82051282, 93.07692308, 93.33333333, 93.58974359, 93.84615385, 94.1025641, 94.35897436, 94.61538462, 94.87179487, 95.12820513, 95.38461538, 95.64102564, 95.8974359, 96.15384615, 96.41025641, 96.66666667, 96.92307692, 97.17948718, 97.43589744, 97.69230769, 97.94871795, 98.20512821, 98.46153846, 98.71794872, 98.97435897, 99.23076923, 99.48717949, 99.74358974, 100}
    };

    public Weapon(int index, int infusion, double weight, int durability, SpecialEffect specialEffects, int strengthRequirement, int dexterityRequirement, int faithRequirement, int intelligenceRequirement, int stability, double physicalBaseAttack, double magicBaseAttack, double fireBaseAttack, double lightningBaseAttack, double darkBaseAttack, int bleedBuildup, int poisonBuildup, int frostBuildup, int criticalMod, double physicalBlock, double magicBlock, double fireBlock, double lightningBlock, double darkBlock, double strengthScaling, double dexterityScaling, double intelligenceScaling, double faithScaling, double luckScaling, int physicalSaturationIndex, int magicSaturationIndex, int fireSaturationIndex, int lightningSaturationIndex, int darkSaturationIndex, boolean blessed, boolean hollow, boolean catalyst, boolean infusable) {
        this.index = index;
        this.weight = weight;
        this.durability = durability;
        this.specialEffects = specialEffects;
        this.blessed = blessed;
        this.hollow = hollow;
        this.catalyst = catalyst;
        this.strengthRequirement = strengthRequirement;
        this.dexterityRequirement = dexterityRequirement;
        this.faithRequirement = faithRequirement;
        this.intelligenceRequirement = intelligenceRequirement;
        this.stability = stability;
        this.infusion = infusion;
        this.physicalBaseAttack = physicalBaseAttack;
        this.magicBaseAttack = magicBaseAttack;
        this.fireBaseAttack = fireBaseAttack;
        this.lightningBaseAttack = lightningBaseAttack;
        this.darkBaseAttack = darkBaseAttack;
        this.bleedBuildup = bleedBuildup;
        this.poisonBuildup = poisonBuildup;
        this.frostBuildup = frostBuildup;
        this.criticalMod = criticalMod;
        this.physicalBlock = physicalBlock;
        this.magicBlock = magicBlock;
        this.fireBlock = fireBlock;
        this.lightningBlock = lightningBlock;
        this.darkBlock = darkBlock;
        this.strengthScaling = strengthScaling;
        this.dexterityScaling = dexterityScaling;
        this.intelligenceScaling = intelligenceScaling;
        this.faithScaling = faithScaling;
        this.luckScaling = luckScaling;
        this.physicalSaturationIndex = physicalSaturationIndex;
        this.magicSaturationIndex = magicSaturationIndex;
        this.fireSaturationIndex = fireSaturationIndex;
        this.lightningSaturationIndex = lightningSaturationIndex;
        this.darkSaturationIndex = darkSaturationIndex;
        this.infusable = infusable;
    }

    public double calculateBonusPhysicalAttack(int str, int dex, int fth, int luck){
        double scalingCoefficient = 0;
        if(str>99){str=99;}
        if(dex>99){dex=99;}
        if(fth>99){fth=99;}
        if(luck>99){luck=99;}

        scalingCoefficient += strengthScaling/100 * saturationCurves[physicalSaturationIndex][str]/100;
        scalingCoefficient += dexterityScaling/100 * saturationCurves[physicalSaturationIndex][dex]/100;

        if(blessed){
            scalingCoefficient += faithScaling/100 * saturationCurves[physicalSaturationIndex][fth]/100;
        }

        if(hollow){
            scalingCoefficient += luckScaling/100 * saturationCurves[physicalSaturationIndex][luck]/100;
        }

        return physicalBaseAttack * scalingCoefficient;
    }

    public double calculateBonusMagicAttack(int inte, int fth){
        double scalingCoefficient = 0;

        if(inte>99){inte=99;}
        if(fth>99){fth=99;}

        if(index == 234567){ // INDEX for "Golden Ritual Spear"
            scalingCoefficient += faithScaling/100 * saturationCurves[magicSaturationIndex][fth]/100;
        }
        else{
            scalingCoefficient += intelligenceScaling/100 * saturationCurves[magicSaturationIndex][inte]/100;
        }

        return magicBaseAttack * scalingCoefficient;
    }

    public double calculateBonusFireAttack(int inte, int fth){
        double scalingCoefficient = 0;

        if(inte>99){inte=99;}
        if(fth>99){fth=99;}

        scalingCoefficient += faithScaling/100 * saturationCurves[fireSaturationIndex][fth]/100;
        scalingCoefficient += intelligenceScaling/100 * saturationCurves[fireSaturationIndex][inte]/100;

        return fireBaseAttack * scalingCoefficient;
    }

    public double calculateBonusLightningAttack(int fth){
        double scalingCoefficient = 0;

        if(fth>99){fth=99;}

        scalingCoefficient += faithScaling/100 * saturationCurves[lightningSaturationIndex][fth]/100;

        return lightningBaseAttack * scalingCoefficient;
    }

    public double calculateBonusDarkAttack(int inte, int fth) {
        double scalingCoefficient = 0;

        if(inte>99){inte=99;}
        if(fth>99){fth=99;}

        scalingCoefficient += faithScaling / 100 * saturationCurves[darkSaturationIndex][fth] / 100;
        scalingCoefficient += intelligenceScaling / 100 * saturationCurves[darkSaturationIndex][inte] / 100;

        return darkBaseAttack * scalingCoefficient;
    }

    public boolean isBlessed() {
        return blessed;
    }

    public boolean isHollow() {
        return hollow;
    }

    public boolean isCatalyst() {
        return catalyst;
    }

    public int getInfusion() {
        return infusion;
    }

    public boolean isInfusable() {
        return infusable;
    }

    public int getStrengthRequirement() {
        return strengthRequirement;
    }

    public int getDexterityRequirement() {
        return dexterityRequirement;
    }

    public int getFaithRequirement() {
        return faithRequirement;
    }

    public int getIntelligenceRequirement() {
        return intelligenceRequirement;
    }

    public int getStability() {
        return stability;
    }

    public int getBleedBuildup() {
        return bleedBuildup;
    }

    public int getPoisonBuildup() {
        return poisonBuildup;
    }

    public int getFrostBuildup() {
        return frostBuildup;
    }

    public int getCriticalMod() {
        return criticalMod;
    }

    public double getPhysicalBlock() {
        return physicalBlock;
    }

    public double getMagicBlock() {
        return magicBlock;
    }

    public double getFireBlock() {
        return fireBlock;
    }

    public double getLightningBlock() {
        return lightningBlock;
    }

    public double getDarkBlock() {
        return darkBlock;
    }

    public double getPhysicalBaseAttack() {
        return physicalBaseAttack;
    }

    public double getMagicBaseAttack() {
        return magicBaseAttack;
    }

    public double getFireBaseAttack() {
        return fireBaseAttack;
    }

    public double getLightningBaseAttack() {
        return lightningBaseAttack;
    }

    public double getDarkBaseAttack() {
        return darkBaseAttack;
    }
}
