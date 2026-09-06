import React from "react";
import { View, Text } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { colors } from "@/constants/colors";
import { LinearGradient } from "expo-linear-gradient";

export default function AvailableBalanceCard() {
  const data = {
    availableToSpend: "R$ 6.150",
    safeMargin: "R$ 1.250",
  };

  return (
    <View
      className="overflow-hidden rounded-[24px]"
      style={{ backgroundColor: colors.primary }}
    >
      <LinearGradient
        colors={[colors.secondary, "#5B21B6", colors.primary]}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={{ padding: 20 }}
      >
        {/* Decoração */}
        <View
          className="absolute -left-19 -top-12 h-40 w-40 rounded-full"
          style={{
            backgroundColor: colors.secondary,
            opacity: 0.4,
          }}
        />

        <View className="flex-row">
          {/* Disponível para gastar */}
          <View className="flex-[1.2] justify-center pr-4">
            <View className="flex-row items-center">
              <Text className="text-xs font-medium text-white/80">
                Disponível para gastar
              </Text>

              <Ionicons
                name="information-circle-outline"
                size={14}
                color="rgba(255,255,255,0.7)"
                style={{ marginLeft: 4 }}
              />
            </View>

            <Text
              className="mt-1 text-[28px] font-bold text-white"
              numberOfLines={1}
              adjustsFontSizeToFit
            >
              {data.availableToSpend}
            </Text>

            <Text className="mt-1 text-[11px] leading-4 text-white/70">
              Após contas e compromissos futuros.
            </Text>
          </View>

          {/* Divisor */}
          <View
            className="w-[1px]"
            style={{
              backgroundColor: "rgba(255, 255, 255, 0.2)",
            }}
          />

          {/* Margem segura */}
          <View className="flex-1 justify-center pl-4">
            <View className="mb-1 flex-row items-center">
              <View
                className="mr-2 h-6 w-6 items-center justify-center rounded-lg"
                style={{
                  backgroundColor: "rgba(255, 255, 255, 0.15)",
                }}
              >
                <Ionicons
                  name="shield-checkmark-outline"
                  size={12}
                  color={colors.success}
                />
              </View>

              <Text className="text-xs font-medium text-white/80">
                Margem segura
              </Text>
            </View>

            <Text
              className="text-[22px] font-bold text-white"
              numberOfLines={1}
              adjustsFontSizeToFit
            >
              {data.safeMargin}
            </Text>

            <Text className="mt-1 text-[11px] leading-4 text-white/70">
              Uso livre de riscos.
            </Text>
          </View>
        </View>
      </LinearGradient>
    </View>
  );
}