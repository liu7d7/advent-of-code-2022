Imports System
Imports System.IO

Module Program
  Sub Main(args As String())
    Dim strs = File.ReadAllLines("in.txt")
    Dim l = New List(Of Int64) From {0}
    For Each s in strs
      If s = "" Then
        l.Add(0)
      Else
        Dim n = Int(s)
        l.Item(l.Count - 1) += n
      End If
    Next s
    l.Sort()
    Console.WriteLine(l.Item(l.Count - 1))
    Console.WriteLine(l.Item(l.Count - 1) + l.Item(l.Count - 2) + l.Item(l.Count - 3))
  End Sub
End Module
