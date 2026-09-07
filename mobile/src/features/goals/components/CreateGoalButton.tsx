import { colors } from "@/constants/colors";
import { Ionicons } from "@expo/vector-icons";
import { Pressable, Text } from "react-native";

type CreateGoalButtonProps = {
  onPress?: () => void;
};

export function CreateGoalButton({ onPress }: CreateGoalButtonProps) {
  return (
    <Pressable 
      onPress={onPress}
      className="flex-row items-center justify-center rounded-2xl border border-dashed border-slate-300 bg-white py-4 active:opacity-70"
    >
      <Ionicons name="add-circle-outline" size={20} color={colors.primary} />
      <Text 
        className="ml-2 text-sm font-semibold"
        style={{ color: colors.primary }}
      >
        Criar nova meta
      </Text>
    </Pressable>
  );
}