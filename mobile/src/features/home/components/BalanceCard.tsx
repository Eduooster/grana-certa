import { colors } from "@/constants/colors";
import { cardShadow } from "@/constants/layout";
import { Feather, Ionicons } from "@expo/vector-icons";
import { LinearGradient } from "expo-linear-gradient";
import { Pressable, Text, View } from "react-native";


export type BalanceCardType = {
  balance: number;
  committed: number;
  connectedAccounts: number;
};

type BalanceCardProps = {
  data: BalanceCardType;
};





export function BalanceCard({ data }: BalanceCardProps) {
  const available = data.balance - data.committed;

  const formatCurrency = (value: number) => {
    return value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });
  };

  return (
    <View className="mb-6" style={cardShadow}>
      <View className="overflow-hidden rounded-[28px]">
        <LinearGradient
          colors={[colors.secondary, "#5B21B6", colors.primary]}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 1 }}
          style={{ padding: 24 }}
        >
          <View className="flex-row items-center justify-between">
            <View className="flex-row items-center gap-2">
              <Text className="text-xs font-semibold uppercase tracking-wider text-violet-200/80">
                Saldo atual
              </Text>
              <Pressable hitSlop={12}>
                <Feather name="eye" size={16} color="#DDD6FE" />
              </Pressable>
            </View>

            <View className="rounded-full bg-white/15 px-3 py-1.5">
              <Text className="text-xs font-medium text-violet-50">
                {data.connectedAccounts} contas
              </Text>
            </View>
          </View>

          <View className="mt-5">
            <Text className="text-[34px] font-extrabold tracking-tight text-white">
              {formatCurrency(data.balance)}
            </Text>
            <Text className="mt-1 text-sm font-medium text-violet-200/80">
              Sincronizado agora
            </Text>
          </View>

          <View className="mt-6 flex-row items-center justify-between border-t border-white/15 pt-5">
            <View>
              <Text className="text-xs font-medium text-violet-300">Comprometido</Text>
              <Text className="mt-1 text-base font-semibold text-violet-50">
                {formatCurrency(data.committed)}
              </Text>
            </View>

            <View className="items-end">
              <Text className="text-xs font-medium text-violet-300">Disponível estimado</Text>
              <View className="mt-1 flex-row items-center gap-2">
                <Text className="text-base font-bold text-white">
                  {formatCurrency(available)}
                </Text>
                <Pressable hitSlop={12}>
                  <Feather name="eye-off" size={14} color="#C4B5FD" />
                </Pressable>
              </View>
            </View>
          </View>
        </LinearGradient>
      </View>
    </View>
  );
}