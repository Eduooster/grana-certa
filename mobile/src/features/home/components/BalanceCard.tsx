import { colors } from "@/constants/colors";
import { cardShadow } from "@/constants/layout";
import { Feather } from "@expo/vector-icons";
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
    <View className="mb-5" style={[cardShadow, { shadowColor: "#5424A8", shadowOpacity: 0.2, shadowRadius: 22, elevation: 7 }]}>
      <View className="overflow-hidden rounded-[28px]">
        <LinearGradient
          colors={[colors.primary, "#E6E6FA", colors.primary]}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 1 }}
          style={{ padding: 24, minHeight: 226 }}
        >
          <View pointerEvents="none" className="absolute -right-12 top-2 h-44 w-72 opacity-30">
            <View className="absolute left-0 top-[112px] h-[2px] w-[64px] rotate-[-18deg] rounded-full bg-violet-100" />
            <View className="absolute left-[58px] top-[94px] h-[2px] w-[58px] rotate-[12deg] rounded-full bg-violet-100" />
            <View className="absolute left-[112px] top-[105px] h-[2px] w-[65px] rotate-[-28deg] rounded-full bg-violet-100" />
            <View className="absolute left-[170px] top-[75px] h-[2px] w-[65px] rotate-[8deg] rounded-full bg-violet-100" />
            <View className="absolute left-[229px] top-[84px] h-[2px] w-[58px] rotate-[-16deg] rounded-full bg-violet-100" />
            {[[0, 107], [55, 89], [109, 100], [167, 70], [226, 79]].map(([left, top]) => (
              <View key={`${left}-${top}`} className="absolute h-3 w-3 rounded-full border-2 border-violet-100 bg-violet-500" style={{ left, top }} />
            ))}
          </View>
          <View pointerEvents="none" className="absolute -right-10 -top-12 h-44 w-44 rounded-full border border-white/10" />
          <View pointerEvents="none" className="absolute -right-3 -top-5 h-32 w-32 rounded-full border border-white/10" />
          <View className="flex-row items-center justify-between">
            <View className="flex-row items-center gap-2">
              <Text className="text-xs font-semibold uppercase tracking-wider text-violet-200/80">
                Saldo atual
              </Text>
              <Pressable hitSlop={12}>
                <Feather name="eye" size={17} color="#DDD6FE" strokeWidth={1.8} />
              </Pressable>
            </View>

            <View className="rounded-full bg-white/15 px-3 py-1.5">
              <Text className="text-xs font-medium text-violet-50">
                {data.connectedAccounts} contas
              </Text>
            </View>
          </View>

          <View className="mt-5">
            <Text className="text-[34px] font-extrabold tracking-[-1px] text-white">
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
                  <Feather name="eye-off" size={15} color="#C4B5FD" strokeWidth={1.8} />
                </Pressable>
              </View>
            </View>
          </View>
        </LinearGradient>
      </View>
    </View>
  );
}
