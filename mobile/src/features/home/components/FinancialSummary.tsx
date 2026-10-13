import { colors } from "@/constants/colors";
import { Feather } from "@expo/vector-icons";
import { Text, View } from "react-native";

type FinancialSummaryProps = {
  income: number;
  expenses: number;
  result: number;
};

export function FinancialSummary({ income, expenses, result }: FinancialSummaryProps) {
  const formatCurrency = (value: number) =>
    value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    });

  const items = [
    { label: "Receitas", value: formatCurrency(income), color: colors.primary, icon: "trending-up" as const },
    { label: "Despesas", value: formatCurrency(expenses), color: colors.primary, icon: "trending-down" as const },
    { label: "Resultado", value: `+${formatCurrency(result)}`, color: colors.primary, icon: "activity" as const },
  ];

  return (
    <View 
      className="mb-7 flex-row items-center rounded-[24px] border border-white/80 bg-white/90 px-2 py-4 shadow-sm"
      style={{ 
        shadowColor: "#988bb5", 
        shadowOffset: { width: 0, height: 8 }, 
        shadowOpacity: 0.07, 
        shadowRadius: 2, 
        elevation: 3 
      }}
    >
      {items.map((item, index) => (
        <View key={item.label} className="flex-1 items-center px-1 bg-white">
         
          <View className="mb-2.5 h-9 w-9 items-center justify-center rounded-full" style={{  }}>
            <Feather name={item.icon} size={17} color={item.color} strokeWidth={2} />
          </View>
          
          <Text className="text-[11px] font-medium tracking-wide text-slate-500">{item.label}</Text>
          
          <Text className="mt-1 text-[15px] font-bold tracking-tight" style={{ color: item.color }} numberOfLines={1} adjustsFontSizeToFit>
            {item.value}
          </Text>

       
          {index < items.length - 1 && (
            <View className="absolute right-0 top-3 h-[50px] w-px bg-violet-100" />
          )}
        </View>
      ))}
    </View>
  );
}
