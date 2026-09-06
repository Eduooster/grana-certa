import { View, Text, TextInput, Pressable } from "react-native";
import { Ionicons } from "@expo/vector-icons";

type SimulationInputProps = {
  amount: string;
  onChangeAmount: (value: string) => void;
  onSimulate: () => void;
};

export function SimulationInput({
  amount,
  onChangeAmount,
  onSimulate,
}: SimulationInputProps) {
  return (
    <View className="mb-4 flex-row items-center rounded-2xl border border-slate-200 bg-white px-4">
      <Text className="mr-2 text-base font-medium text-slate-500">
        R$
      </Text>

      <TextInput
        value={amount}
        onChangeText={onChangeAmount}
        placeholder="0,00"
        placeholderTextColor="#94A3B8"
        keyboardType="decimal-pad"
        className="h-14 flex-1 text-lg font-semibold text-slate-900"
      />

      <Pressable
        onPress={onSimulate}
        disabled={!amount}
        className="h-10 w-10 items-center justify-center rounded-xl bg-primary active:opacity-80 disabled:opacity-40"
      >
        <Ionicons
          name="analytics-outline"
          size={19}
          color="black"
        />
      </Pressable>
    </View>
  );
}