import { View, Text, Pressable, ScrollView } from "react-native";
import { Ionicons } from "@expo/vector-icons";

import { colors } from "@/constants/colors";
import { ScreenContainer } from "@/app/components/layouts/ScreenContainer";

export default function GoalsScreen() {
  return (
    <ScreenContainer>
      
      <View className="mb-6 pt-5">
        <Text className="text-2xl font-bold text-slate-900">
          Metas
        </Text>

        <Text className="mt-1 text-sm text-slate-500">
          Defina objetivos e acompanhe seu progresso.
        </Text>
      </View>

      {/* Meta principal */}
      <View className="mb-4 rounded-2xl bg-white p-5">
        <View className="mb-4 flex-row items-center justify-between">
          <View className="flex-row items-center">
            <View className="h-10 w-10 items-center justify-center rounded-xl bg-indigo-50">
              <Ionicons
                name="wallet-outline"
                size={21}
                color={colors.primary}
              />
            </View>

            <View className="ml-3">
              <Text className="text-xs text-slate-500">
                Meta de economia
              </Text>

              <Text className="text-base font-bold text-slate-900">
                Economizar este mês
              </Text>
            </View>
          </View>

          <Ionicons
            name="ellipsis-horizontal"
            size={20}
            color="#94A3B8"
          />
        </View>

        <View className="mb-2 flex-row items-end justify-between">
          <Text className="text-2xl font-bold text-slate-900">
            R$ 650
          </Text>

          <Text className="text-sm text-slate-500">
            de R$ 1.000
          </Text>
        </View>

        {/* Progress */}
        <View className="h-2 overflow-hidden rounded-full bg-slate-100">
          <View
            className="h-full rounded-full bg-primary"
            style={{ width: "65%" }}
          />
        </View>

        <Text className="mt-3 text-xs text-slate-500">
          Faltam R$ 350 para atingir sua meta.
        </Text>
      </View>

      {/* Limites de gastos */}
      <View className="mb-4 rounded-2xl bg-white p-5">
        <View className="mb-4">
          <Text className="text-base font-bold text-slate-900">
            Limites de gastos
          </Text>

          <Text className="mt-1 text-xs text-slate-500">
            Acompanhe quanto ainda pode gastar em cada categoria.
          </Text>
        </View>

        {/* iFood */}
        <View className="mb-4">
          <View className="mb-2 flex-row justify-between">
            <View className="flex-row items-center">
              <Ionicons
                name="restaurant-outline"
                size={18}
                color="#64748B"
              />

              <Text className="ml-2 text-sm font-medium text-slate-700">
                Alimentação
              </Text>
            </View>

            <Text className="text-sm font-semibold text-slate-900">
              R$ 120 / R$ 400
            </Text>
          </View>

          <View className="h-2 rounded-full bg-slate-100">
            <View
              className="h-full rounded-full bg-primary"
              style={{ width: "30%" }}
            />
          </View>
        </View>

        {/* Lazer */}
        <View>
          <View className="mb-2 flex-row justify-between">
            <View className="flex-row items-center">
              <Ionicons
                name="game-controller-outline"
                size={18}
                color="#64748B"
              />

              <Text className="ml-2 text-sm font-medium text-slate-700">
                Lazer
              </Text>
            </View>

            <Text className="text-sm font-semibold text-slate-900">
              R$ 280 / R$ 300
            </Text>
          </View>

          <View className="h-2 rounded-full bg-slate-100">
            <View
              className="h-full rounded-full bg-amber-400"
              style={{ width: "93%" }}
            />
          </View>
        </View>
      </View>

      {/* Nova meta */}
      <Pressable className="flex-row items-center justify-center rounded-2xl border border-dashed border-slate-300 bg-white py-4 active:opacity-70">
        <Ionicons
          name="add-circle-outline"
          size={20}
          color={colors.primary}
        />

        <Text className="ml-2 text-sm font-semibold text-primary">
          Criar nova meta
        </Text>
      </Pressable>
    </ScreenContainer>
  );
}