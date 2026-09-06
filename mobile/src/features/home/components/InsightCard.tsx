import { colors } from "@/constants/colors";
import { cardShadow } from "@/constants/layout";
import { Feather, Ionicons } from "@expo/vector-icons";
import { Pressable, Text, View } from "react-native";


export function InsightCard() {
  return (
    <View className="mb-8">
      <View className="mb-4 flex-row items-center justify-between px-1">
        <Text className="text-xl font-bold" style={{ color: "#1F2937" }}>
          O GranaCerta percebeu
        </Text>
        <View className="flex-row items-center gap-1.5">
          <Feather name="zap" size={14} color={colors.secondary} />
          <Text className="text-xs font-bold uppercase tracking-wide" style={{ color: colors.secondary }}>
            Análise automática
          </Text>
        </View>
      </View>

      <Pressable 
        className="flex-row items-center rounded-[24px] p-5"
        style={[{ backgroundColor: "#F5F3FF" }, cardShadow]}
      >
        <View 
          className="mr-4 h-12 w-12 items-center justify-center rounded-full"
          style={{ backgroundColor: colors.primary }}
        >
          <Feather name="coffee" size={20} color="#FFFFFF" />
        </View>

        <View className="flex-1">
          <Text className="mb-1.5 text-sm font-bold leading-tight" style={{ color: "#1F2937" }}>
            Você gastou 18% menos com delivery
          </Text>
          <Text className="text-xs leading-relaxed" style={{ color: "#6B7280" }}>
            Nesse ritmo, você pode gastar cerca de R$ 240 a menos com delivery este mês.
          </Text>
        </View>

        <View className="ml-2 pl-2">
          <Feather name="chevron-right" size={20} color="#9CA3AF" />
        </View>
      </Pressable>
    </View>
  );
}