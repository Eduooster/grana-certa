import { View, Text, Pressable } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useRouter } from "expo-router";

import { colors } from "@/constants/colors";

export function BeforeSpendDetailsHeader() {
  const router = useRouter();

  return (
    <View className="mb-6 w-full flex-row items-center justify-between">
     
      <Pressable
        onPress={() => router.back()}
        className="h-10 w-10 items-center justify-center rounded-full active:bg-slate-100"
        hitSlop={8}
      >
        <Ionicons
          name="arrow-back"
          size={22}
          color="#0F172A"
        />
      </Pressable>

      
      <Text className="mx-3 flex-1 text-center text-xl font-bold text-slate-900">
        Detalhes da simulação
      </Text>

      
      <Pressable
        onPress={() => {
          
        }}
        className="h-10 w-10 items-center justify-center rounded-full active:bg-slate-100"
        hitSlop={8}
      >
        <Ionicons
          name="notifications-outline"
          size={22}
          color={colors.primary}
        />
      </Pressable>
    </View>
  );
}