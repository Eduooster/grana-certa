import { View, Text, TextInput, Pressable } from 'react-native';
import { Feather } from '@expo/vector-icons';

type SimulationInputProps = {
  amount: string;
  onChangeAmount: (value: string) => void;
  onSimulate: () => void;
};

export function SimulationInput({ amount, onChangeAmount, onSimulate }: SimulationInputProps) {
  return (
    <View
      className="mb-4 flex-row items-center rounded-[20px] border border-violet-100 bg-white px-4"
      style={{
        shadowColor: '#35245B',
        shadowOffset: { width: 0, height: 5 },
        shadowOpacity: 0.055,
        shadowRadius: 13,
        elevation: 2,
      }}>
      <Text className="mr-2 text-base font-semibold text-violet-600">R$</Text>

      <TextInput
        value={amount}
        onChangeText={onChangeAmount}
        placeholder="0,00"
        placeholderTextColor="#94A3B8"
        keyboardType="decimal-pad"
        className="h-14 flex-1 text-lg font-semibold tracking-tight text-slate-900"
      />

      <Pressable
        onPress={onSimulate}
        disabled={!amount}
        className="h-10 w-10 items-center justify-center rounded-xl bg-violet-600 active:opacity-80 disabled:opacity-40">
        <Feather name="arrow-up-right" size={19} color="white" strokeWidth={2} />
      </Pressable>
    </View>
  );
}
